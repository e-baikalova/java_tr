package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.GroupData;

public class ContactCreationTests extends TestBase {

  @Test
  public void testContactCreation() throws Exception {
    String groupName = "testgroup1";
    //check that group exists
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData(groupName, null, null));
    };
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().initContanctCreation();
    app.getContactHelper().fillContactForm(
        new ContactData("test_name4", "test_lname", "test address",
            "test@email.com", "1263547", groupName), true);
    app.getContactHelper().submitContactCreation();
    app.getNavigationHelper().gotoHomepage();
  }

}
