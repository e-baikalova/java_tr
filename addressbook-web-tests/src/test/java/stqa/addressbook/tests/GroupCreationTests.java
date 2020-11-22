package stqa.addressbook.tests;

import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    GroupData group = new GroupData().withName("test1");
    app.group().create(group);
    Groups after = app.group().all();
    //check that groups amount is changed
    assertThat(after.size(), equalTo(before.size() + 1));
    //(g) -> g.getId()).max().getAsInt()  - anonymous function that gets MAX ID of all groups
//    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    //compare groups sets
    assertThat(after, equalTo(
        before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

}
