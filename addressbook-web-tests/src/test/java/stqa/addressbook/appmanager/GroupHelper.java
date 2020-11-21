package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class GroupHelper extends HelperBase {

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

  public void selectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
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
    returnToGroups();
  }

  public int getGroupCount() {
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

  public String getName(int index) {
    return list().get(index).getName();
  }

  public void modifyGroup(int index, GroupData group) {
    selectGroup(index);
    initGroupModification();
    fillGroupForm(group);
    submitGroupModification();
    returnToGroups();
  }

  public void delete(int index) {
    selectGroup(index);
    deleteSelected();
    returnToGroups();
  }

}
