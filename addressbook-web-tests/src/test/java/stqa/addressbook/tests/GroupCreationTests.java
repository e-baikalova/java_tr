package stqa.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
//    list.add(new Object[] {new GroupData().withName("test 1").withHeader("header 1").withFooter("footer 1")});
//    list.add(new Object[] {new GroupData().withName("test 2").withHeader("header 2").withFooter("footer 2")});
//    list.add(new Object[] {new GroupData().withName("test 3").withHeader("header 3").withFooter("footer 3")});
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }

  @DataProvider
  public Iterator<Object[]> invalidGroups() throws IOException {
    List<Object[]> list = new ArrayList<Object[]>();
//    list.add(new Object[] {new GroupData().withName("test 1'").withHeader("header 1").withFooter("footer 1")});
//    list.add(new Object[] {new GroupData().withName("test 2").withHeader("header 2").withFooter("footer 2")});
//    list.add(new Object[] {new GroupData().withName("test 3").withHeader("header 3").withFooter("footer 3")});
    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/invalidGroups.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[] {new GroupData().withName(split[0]).withHeader(split[1]).withFooter(split[2])});
      line = reader.readLine();
    }
    return list.iterator();
  }


  @Test(dataProvider = "validGroups")
  public void testGroupCreation(GroupData group) throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
    app.group().create(group);
    //check that groups amount is changed
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();
    //(g) -> g.getId()).max().getAsInt()  - anonymous function that gets MAX ID of all groups
//    group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt());
    //compare groups sets
    assertThat(after, equalTo(
        before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(dataProvider = "invalidGroups")
  public void testBadGroupCreation(GroupData group) throws Exception {
    app.goTo().groupPage();
    Groups before = app.group().all();
//    GroupData group = new GroupData().withName("test2'");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();
    assertThat(after, equalTo(before));
  }
}
