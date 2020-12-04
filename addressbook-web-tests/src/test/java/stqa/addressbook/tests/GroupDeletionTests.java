package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupDeletion() throws Exception {
    app.goTo().groupPage();
    Groups before = app.db().groups();
    //get any element from the set
    GroupData deletedGroup = before.iterator().next();
    //remove last group
    app.group().delete(deletedGroup);
    assertThat(app.group().count(), equalTo(before.size() - 1));
    Groups after = app.db().groups();
//    //check that groups amount is changed
//    assertThat(after.size(), equalTo(before.size()-1));
    //compare 2 lists: original list and modified list
//    for (int i=0; i<after.size(); i++){
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    assertThat(after, equalTo(before.without(deletedGroup)));
    verifyGroupListInUi();
  }


}
