package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModificationFromContactsForm() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().initContanctModification();
    app.getContactHelper().fillContactForm(new ContactData("mod_test_name", "test_lname", "test address", "test@email.com", "1263547"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomepage();
  }

  @Test
  public void testContactModificationFromContactDetails() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().openContactDetails();
    app.getContactHelper().initContanctModificationFromDetails();
    app.getContactHelper().fillContactForm(new ContactData("mod_test_name4", "test_lname_555", "test address", "test@email.com", "1263547"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomepage();
  }

}
