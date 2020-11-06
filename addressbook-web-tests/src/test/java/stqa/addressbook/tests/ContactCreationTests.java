package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().initContanctCreation();
    app.getContactHelper().fillContactForm(
        new ContactData("test_name2", "test_lname", "test address",
            "test@email.com", "1263547", "testgroup"), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomepage();
  }

}
