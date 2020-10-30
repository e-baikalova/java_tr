package stqa.addressbook.tests;

import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.goToGroups();
    app.selectGroup();
    app.deleteSelectedGroups();
    app.returnToGroups();
  }

}
