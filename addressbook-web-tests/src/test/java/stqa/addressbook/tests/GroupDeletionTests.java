package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

import java.util.Set;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    Set<GroupData> before = app.group().all();
    //get any element from the set
    GroupData deletedGroup = before.iterator().next();
    //remove last group
    app.group().delete(deletedGroup);
    Set<GroupData> after = app.group().all();
    //check that groups amount is changed
    Assert.assertEquals(after.size(), before.size()-1);

    //we remove the group from the original list to check that required group was deleted
    before.remove(deletedGroup);
    //compare 2 lists: original list and modified list
//    for (int i=0; i<after.size(); i++){
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    Assert.assertEquals(before, after);
  }


}
