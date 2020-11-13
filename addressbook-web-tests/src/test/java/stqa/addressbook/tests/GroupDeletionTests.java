package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test_gr1", "test_1", "test_1"));
    }
    int before = app.getGroupHelper().getGroupCount();
    //remove last group
    app.getGroupHelper().selectGroup(before-1);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroups();
    int after = app.getGroupHelper().getGroupCount();
    //check that groups amount is changed
    Assert.assertEquals(after, before - 1);
  }

}
