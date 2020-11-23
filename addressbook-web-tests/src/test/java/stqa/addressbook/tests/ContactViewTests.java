package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.GroupData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactViewTests extends TestBase {
  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homepage();
    String groupName = "testgroup";
    if (app.contact().all().size() == 0) {
      //check that group exists
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName(groupName));
      }
      else {
        groupName = app.group().getName();
      }
      app.contact().createContact(new ContactData().
              withFirstname("Ivan").
              withLastname("Ivanov").
              withAddress("test address").
              withEmail("test1@email.com").
              withEmail2("test2@email.com").
              withEmail3("test3@email.com").
              withPhoneNumber("+7(999)111").
              withMobileNumber("22 222").
              withWorkNumber("33-333").
              withSecAddress("secondary address").
              withGroup(groupName),
          true);
    }
  }

  @Test
  public void testContactPhones(){
    app.goTo().homepage();
    ContactData contact = app.contact().all().iterator().next();
    ContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));
    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));
  }

  //we filter empty entries and join non-empty elements
  private String mergePhones(ContactData contact) {
    return Arrays.asList(contact.getPhoneNumber(), contact.getMobileNumber(), contact.getWorkNumber()).
        stream().filter((s) -> ! s.equals("")).
        map(ContactViewTests::cleaned).
        collect(Collectors.joining("\n"));
  }

  private String mergeEmails(ContactData contact) {
    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).
        stream().filter((s) -> ! s.equals("")).
        map(ContactViewTests::cleaned).
        collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone){
    return phone.replaceAll("\\s","").replaceAll("[-()]","");
  }
}
