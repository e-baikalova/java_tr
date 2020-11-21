package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreationTests extends TestBase {

  @Test(enabled = false)
  public void testGroupCreation() throws Exception {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();
//    int before = app.getGroupHelper().getGroupCount();
    GroupData group = new GroupData("test1234", null, null);
    app.group().create(group);
    List<GroupData> after = app.group().list();
    //check that groups amount is changed
    Assert.assertEquals(after.size(), before.size() + 1);

//    //find MAX id within groups
//    int max = 0;
//    for (GroupData g: after){
//      if (g.getId() > max) {
//        max = g.getId();
//      }
//    }

//    //find MAX id within groups using lambda function
//
//    group.setId( after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId() );
    before.add(group);

    //list sorting
    Comparator<? super GroupData> byId = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(byId);
    after.sort(byId);

    //сравнение множеств
    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));
  }

}
