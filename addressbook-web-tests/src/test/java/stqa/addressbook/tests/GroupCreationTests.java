package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

import java.util.Set;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Set<GroupData> before = app.group().all();
    GroupData group = new GroupData().withName("test1");
    app.group().create(group);
    Set<GroupData> after = app.group().all();
    //check that groups amount is changed
    Assert.assertEquals(after.size(), before.size() + 1);
    //(g) -> g.getId()).max().getAsInt()  - anonymous function that gets MAX ID of all groups
    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    before.add(group);
    //compare groups sets
    Assert.assertEquals(before, after);
  }

}
