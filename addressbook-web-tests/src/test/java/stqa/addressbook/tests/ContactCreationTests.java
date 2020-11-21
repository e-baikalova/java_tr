package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ContactCreationTests extends TestBase {

  @Test(enabled = false)
  public void testContactCreation() throws Exception {
    String groupName = "testgroup";
    //check that group exists
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData(groupName, null, null));
    }
    else {
      groupName = app.group().getName(0);
    }
    app.goTo().homepage();
    List<ContactData> before = app.contact().list();
    ContactData contact = new ContactData("test_name2", "test_lname", "test address",
        "test@email.com", "1263547", groupName);
    app.contact().createContact(contact, true);
    app.goTo().homepage();
    List<ContactData> after = app.contact().list();
    //check that groups amount is changed
    Assert.assertEquals(after.size(), before.size()+1);

    //find MAX id within contacts
//    int max = 0;
//    for (ContactData c: after){
//      if (c.getId() > max) {
//        max = c.getId();
//      }
//    }

    //find MAX id within groups using lambda function
//
    contact.setId( after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId() );
    before.add(contact);

    //list sorting
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    //сравнение множеств
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
