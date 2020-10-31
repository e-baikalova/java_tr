package stqa.addressbook.tests;

import org.testng.annotations.*;
import stqa.addressbook.model.ContactData;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModification() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().initContanctModification();
    app.getContactHelper().fillContactForm(new ContactData("mod_test_name444", "test_lname", "test address", "test@email.com", "1263547"));
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomepage();
    app.getSessionHelper().logout();
  }
}
