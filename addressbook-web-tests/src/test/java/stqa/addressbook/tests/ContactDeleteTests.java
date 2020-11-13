package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

import java.util.List;

public class ContactDeleteTests extends TestBase {

  @Test
  public void testSingleSelectedContactDelete() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name1", "test_lname", "test address",
          "test@email.com", "1263547", "test123"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectContact(before.size()-1);
    app.getContactHelper().initSelectedContactDelete();
    app.getContactHelper().waitForAlertAndAccept();
    app.getNavigationHelper().gotoHomepage();
    Thread.sleep(5000);
    List<ContactData> after = app.getContactHelper().getContactList();
    //check that contact amount is changed
    Assert.assertEquals(after.size(), before.size()-1);
//    Thread.sleep(5000);
    //we remove the contact from the original list to check that required contact was deleted
    before.remove(before.size()-1);
//    Thread.sleep(5000);
    //compare 2 lists: original list and modified list
//    for (int i=0; i<after.size(); i++){
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    Assert.assertEquals(before, after);

  }

  @Test(enabled = false)
  public void testContactDeleteFromForm() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name2", "test_lname", "test address",
          "test@email.com", "1263547", "test123"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContanctModification(before.size()-1);
    app.getContactHelper().submitContactDeleteFromForm();
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> after = app.getContactHelper().getContactList();
    //check that contact amount is changed
    Assert.assertEquals(after.size(), before.size()-1);

    //we remove the contact from the original list to check that required contact was deleted
    before.remove(before.size()-1);

    //compare 2 lists: original list and modified list
//    for (int i=0; i<after.size(); i++){
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    Assert.assertEquals(before, after);
  }

  @Test(enabled = false)
  public void testAllSelectedContactsDelete() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name3", "test_lname", "test address",
          "test@email.com", "1263547", "test123"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().selectAllContacts();
    app.getContactHelper().initSelectedContactDelete();
    app.getContactHelper().waitForAlertAndAccept();
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> after = app.getContactHelper().getContactList();
    //check that groups contact is changed
    Assert.assertEquals(after.size(), before.size()-1);

    //we remove the contact from the original list to check that required contact was deleted
    before.remove(before.size()-1);
    //compare 2 lists: original list and modified list
//    for (int i=0; i<after.size(); i++){
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    Assert.assertEquals(before, after);
  }

}
