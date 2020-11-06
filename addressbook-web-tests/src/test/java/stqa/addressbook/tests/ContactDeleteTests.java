package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.GroupData;

public class ContactDeleteTests extends TestBase {

  @Test
  public void testSingleSelectedContactDelete() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name1", "test_lname", "test address",
          "test@email.com", "1263547", "test123"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().selectFirstContact();
    app.getContactHelper().initSelectedContactDelete();
    app.getContactHelper().waitForAlertAndAccept();
    app.getNavigationHelper().gotoHomepage();
  }

  @Test
  public void testContactDeleteFromForm() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name2", "test_lname", "test address",
          "test@email.com", "1263547", "test123"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().initContanctModification();
    app.getContactHelper().submitContactDeleteFromForm();
    app.getNavigationHelper().gotoHomepage();
  }

  @Test
  public void testAllSelectedContactsDelete() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name3", "test_lname", "test address",
          "test@email.com", "1263547", "test123"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().selectAllContacts();
    app.getContactHelper().initSelectedContactDelete();
    app.getContactHelper().waitForAlertAndAccept();
    app.getNavigationHelper().gotoHomepage();
  }

}
