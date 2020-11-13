package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test_gr1", "test_1", "test_1"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().selectGroup(before.size()-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test123", "test2145325", "test_gdzsxbz"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroups();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    //check that groups amount is not changed
    Assert.assertEquals(after.size(), before.size());
  }
}
