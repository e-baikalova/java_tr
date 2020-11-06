package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupForm(new GroupData("test123", null, null)); //setting default parameters
    app.getGroupHelper().submitGroupCreation();
    app.getGroupHelper().returnToGroups();
  }

}
