package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.getContactHelper().initContanctCreation();
    app.getContactHelper().fillContactForm(new ContactData("test_name2", "test_lname", "test address", "test@email.com", "1263547"));
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomepage();
  }

}
