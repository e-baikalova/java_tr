package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

import java.util.List;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("testgroup1", "test_1", "test_1"));
    }
  }

  @Test(enabled = false)
  public void testGroupDeletion() throws Exception {
    List<GroupData> before = app.getGroupHelper().getGroupList();
    int index = before.size()-1;
    //remove last group
    app.getGroupHelper().selectGroup(index);
    app.getGroupHelper().deleteSelectedGroups();
    app.getGroupHelper().returnToGroups();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //check that groups amount is changed
    Assert.assertEquals(after.size(), index);

    //we remove the group from the original list to check that required group was deleted
    before.remove(index);
    //compare 2 lists: original list and modified list
//    for (int i=0; i<after.size(); i++){
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    Assert.assertEquals(before, after);
  }

}
