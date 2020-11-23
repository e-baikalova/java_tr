package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    String groupName = "testgroup";
    //check that group exists
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName(groupName));
    }
    else {
      //get any group name from the set
      groupName = app.group().getName();
    }
    app.goTo().homepage();
    Contacts before = app.contact().all();
    ContactData contact = new ContactData().
        withFirstname("test_name2").
        withLastname("test_lname").
        withAddress("test address").
        withEmail("test@email.com").
        withPhoneNumber("1263547").
        withGroup(groupName);
    app.contact().createContact(contact, true);
    app.goTo().homepage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
//    //check that groups amount is changed
//    assertThat(after.size(), equalTo(before.size() + 1));

//    //get MAX id of all contacts
//    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId() );
//    before.add(contact);

    //compare sets
    assertThat(after, equalTo(
        before.withAdded(contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId() ))));
  }

}
