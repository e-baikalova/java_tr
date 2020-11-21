package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      String groupName = "testgroup3";
      //check that group exists
      app.getNavigationHelper().gotoGroupPage();
      if (! app.getGroupHelper().isThereAGroup()) {
        app.getGroupHelper().createGroup(new GroupData(groupName, null, null));
      }
      else {
        groupName = app.getGroupHelper().getGroupName(0);
      }
      app.getContactHelper().createContact(new ContactData("test_name1", "test_lname", "test address",
          "test@email.com", "1263547", groupName), true);
    }
  }

  @Test(enabled = false)
  public void testContactModificationFromContactsForm() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size()-1;
    app.getContactHelper().initContanctModification(index);
    //we save original id for record
    ContactData contact = new ContactData(before.get(index).getId(), "mod_test_name", "test_lname", "test address",
        "test@email.com", "1263547", null);
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> after = app.getContactHelper().getContactList();
    //check that groups amount is not changed
    Assert.assertEquals(after.size(), before.size());

    //check 2 lists
    before.remove(index);
    before.add(contact);

    //list sorting
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    //lists comparison
    Assert.assertEquals(before, after);
  }

  @Test(enabled = false)
  public void testContactModificationFromContactDetails() throws Exception {
//    app.getNavigationHelper().gotoHomepage();
//    if (! app.getContactHelper().isThereAContact()) {
//      String groupName = "testgroup3";
//      //check that group exists
//      app.getNavigationHelper().gotoGroupPage();
//      if (! app.getGroupHelper().isThereAGroup()) {
//        app.getGroupHelper().createGroup(new GroupData(groupName, null, null));
//      }
//      else {
//        groupName = app.getGroupHelper().getGroupName(0);
//      }
//      app.getContactHelper().createContact(new ContactData("test_name1", "test_lname", "test address",
//          "test@email.com", "1263547", groupName), true);
//    }
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> before = app.getContactHelper().getContactList();
    int index = before.size()-1;
    ContactData contact = new ContactData(before.get(index).getId(), "mod_test_name2", "test_lname", "test address",
        "test@email.com", "1263547", null);
    app.getContactHelper().openContactDetails(index);
    app.getContactHelper().initContanctModificationFromDetails();
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> after = app.getContactHelper().getContactList();
    //check that groups amount is not changed
    Assert.assertEquals(after.size(), before.size());
    //check 2 lists
    before.remove(index);
    before.add(contact);

    //list sorting
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    //lists comparison
    Assert.assertEquals(before, after);
  }

}
