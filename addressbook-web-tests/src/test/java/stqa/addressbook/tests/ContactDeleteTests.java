package stqa.addressbook.tests;

import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase {

  @Test
  public void testSingleSelectedContactDelete() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().selectFirstContact();
    app.getContactHelper().initSelectedContactDelete();
    app.getContactHelper().waitForAlertAndAccept();
    app.getNavigationHelper().gotoHomepage();
  }

  @Test
  public void testContactDeleteFromForm() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().initContanctModification();
    app.getContactHelper().submitContactDeleteFromForm();
    app.getNavigationHelper().gotoHomepage();
  }

  @Test
  public void testAllSelectedContactsDelete() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().selectAllContacts();
    app.getContactHelper().initSelectedContactDelete();
    app.getContactHelper().waitForAlertAndAccept();
    app.getNavigationHelper().gotoHomepage();
  }

}
