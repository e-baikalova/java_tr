package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.GroupData;

import java.util.Set;

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
    Set<ContactData> before = app.contact().all();
    ContactData contact = new ContactData().
        withFirstname("test_name2").
        withLastname("test_lname").
        withAddress("test address").
        withEmail("test@email.com").
        withPhone("1263547").
        withGroup(groupName);
    app.contact().createContact(contact, true);
    app.goTo().homepage();
    Set<ContactData> after = app.contact().all();
    //check that groups amount is changed
    Assert.assertEquals(after.size(), before.size()+1);

    //get MAX id of all contacts
    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId() );
    before.add(contact);

    //compare sets
    Assert.assertEquals(before, after);
  }

}
