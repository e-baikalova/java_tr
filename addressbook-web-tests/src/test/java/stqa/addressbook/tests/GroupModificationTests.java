package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification() throws Exception {
    app.getNavigationHelper().gotoGroupPage();
    if (! app.getGroupHelper().isThereAGroup()) {
      app.getGroupHelper().createGroup(new GroupData("test_gr1", "test_1", "test_1"));
    }
    int before = app.getGroupHelper().getGroupCount();
    app.getGroupHelper().selectGroup(before-1);
    app.getGroupHelper().initGroupModification();
    app.getGroupHelper().fillGroupForm(new GroupData("test123", "test2145325", "test_gdzsxbz"));
    app.getGroupHelper().submitGroupModification();
    app.getGroupHelper().returnToGroups();
    int after = app.getGroupHelper().getGroupCount();
    //check that groups amount is not changed
    Assert.assertEquals(after, before);
  }
}
