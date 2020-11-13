package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModificationFromContactsForm() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name", "test_lname", "test address",
          "test@email.com", "1263547","testgroup"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    int before = app.getContactHelper().getContactCount();

    app.getContactHelper().initContanctModification(before-1);
    app.getContactHelper().fillContactForm(new ContactData("mod_test_name", "test_lname", "test address",
        "test@email.com", "1263547", "testgroup"), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomepage();
    int after = app.getContactHelper().getContactCount();
    //check that contact amount is not changed
    Assert.assertEquals(after, before);
  }

  @Test(enabled = false)
  public void testContactModificationFromContactDetails() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name_2", "test_lname", "test address",
          "test@email.com", "1263547", "testgroup"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    int before = app.getContactHelper().getContactCount();
    app.getContactHelper().openContactDetails(before-1);
    app.getContactHelper().initContanctModificationFromDetails();
    app.getContactHelper().fillContactForm(new ContactData("mod_test_name2", "test_lname_555",
        "test address", "test@email.com", "1263547", "testgroup"), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomepage();
    int after = app.getContactHelper().getContactCount();
    //check that contact amount is not changed
    Assert.assertEquals(after, before);
  }

}
