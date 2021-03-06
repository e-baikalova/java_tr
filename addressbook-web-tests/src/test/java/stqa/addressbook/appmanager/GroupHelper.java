package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

  private Groups groupCashe = null;

  public GroupHelper(WebDriver wd) {
    super(wd);
  }

  public void returnToGroups() {
    click(By.linkText("group page"));
  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupForm(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());
    type(By.name("group_header"), groupData.getHeader());
    type(By.name("group_footer"), groupData.getFooter());
  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void deleteSelected() {
    click(By.name("delete"));
  }

  public void initGroupModification() {
    click(By.xpath("(//input[@name='edit'])[2]"));
  }

  public void submitGroupModification() {
    click(By.name("update"));
  }

  public boolean isThereAGroup() {
   return isElementPresent(By.name("selected[]"));
  }

  public void create(GroupData group) {
    initGroupCreation();
    fillGroupForm(group); //setting default parameters
    submitGroupCreation();
    groupCashe = null;
    returnToGroups();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }

  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<GroupData>();
    //find all elements with tag = span and type = group
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//      GroupData group = new GroupData().withId(id).withName(name);
//      groups.add(group);
      groups.add(new GroupData().withId(id).withName(name));
    }
    return groups;
  }

  public Groups all() {
    if (groupCashe != null) {
      return new Groups(groupCashe);
    }
    groupCashe = new Groups();
    //find all elements with tag = span and type = group
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
    for (WebElement element : elements) {
      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
//      GroupData group = new GroupData().withId(id).withName(name);
//      groups.add(group);
      groupCashe.add(new GroupData().withId(id).withName(name));
    }
    return new Groups(groupCashe);
  }

  public String getName() {
    //return name of any group from the set
    return all().iterator().next().getName();
  }

  public void modify(GroupData group) {
    selectGroupById(group.getId());
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    groupCashe = null;
    returnToGroups();
  }

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectGroupById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void delete(int index) {
    selectGroup(index);
    deleteSelected();
    returnToGroups();
  }

  public void delete(GroupData group) {
    selectGroupById(group.getId());
    deleteSelected();
    groupCashe = null;
    returnToGroups();
  }
}
