package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

public class ContactDeleteTests extends TestBase {

  @Test
  public void testSingleSelectedContactDelete() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    int before = app.getContactHelper().getContactCount();
//    if (! app.getContactHelper().isThereAContact()) {
    if (before == 0) {
      app.getContactHelper().createContact(new ContactData("test_name1", "test_lname", "test address",
          "test@email.com", "1263547", "test123"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectContact(before-1);
    app.getContactHelper().initSelectedContactDelete();
    app.getContactHelper().waitForAlertAndAccept();
    app.getNavigationHelper().gotoHomepage();

    int after = app.getContactHelper().getContactCount();
    //check that contact amount is changed
    Assert.assertEquals(after, before-1);

  }

  @Test(enabled = false)
  public void testContactDeleteFromForm() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name2", "test_lname", "test address",
          "test@email.com", "1263547", "test123"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().initContanctModification(before-1);
    app.getContactHelper().submitContactDeleteFromForm();
    app.getNavigationHelper().gotoHomepage();
    int after = app.getContactHelper().getContactCount();
    //check that contact amount is changed
    Assert.assertEquals(after, before-1);
  }

  @Test(enabled = false)
  public void testAllSelectedContactsDelete() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name3", "test_lname", "test address",
          "test@email.com", "1263547", "test123"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().selectAllContacts();
    app.getContactHelper().initSelectedContactDelete();
    app.getContactHelper().waitForAlertAndAccept();
    app.getNavigationHelper().gotoHomepage();
    int after = app.getContactHelper().getContactCount();
    //check that groups contact is changed
    Assert.assertEquals(after, before-1);
  }

}
