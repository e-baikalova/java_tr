package stqa.addressbook.tests;

import org.testng.annotations.*;
import stqa.addressbook.model.ContactData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    app.initContanctCreation();
    app.fillContactForm(new ContactData("test_name", "test_lname", "test address", "test@email.com", "1263547"));
    app.submitContactCreation();
    app.returnToHomePage();
    app.logout();
  }

}
