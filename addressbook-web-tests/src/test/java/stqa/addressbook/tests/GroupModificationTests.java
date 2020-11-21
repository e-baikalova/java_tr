package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();
    if (app.group().list().size() == 0) {
      app.group().create(new GroupData().withName("test1"));
    }
  }

  @Test
  public void testGroupModification() throws Exception {
    List<GroupData> before = app.group().list();
    int index = before.size()-1;
    //we save original id for record
//    GroupData group = new GroupData(before.get(index).getId(), "test1", "test2145325", "test_gdzsxbz");
    GroupData group = new GroupData()
        .withId(before.get(index).getId()).withName("test1").withFooter("test2").withHeader("test3");
    app.group().modifyGroup(index, group);
    List<GroupData> after = app.group().list();
    //check that groups amount is not changed
    Assert.assertEquals(after.size(), before.size());

    //check 2 lists
    before.remove(before.size() - 1);
    before.add(group);

    //list sorting
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    //lists comparison
    Assert.assertEquals(before, after);
  }


}
