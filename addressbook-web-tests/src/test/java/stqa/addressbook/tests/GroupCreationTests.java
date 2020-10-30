package stqa.addressbook.tests;

import org.testng.annotations.*;
import stqa.addressbook.model.GroupData;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goToGroups();
    app.initGroupCreation();
    app.fillGroupForm(new GroupData("test234", "test2", "test3"));
    app.submitGroupCreation();
    app.returnToGroups();
    app.logout();
  }

}
