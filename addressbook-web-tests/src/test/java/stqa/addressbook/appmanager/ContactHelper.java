package stqa.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {

  private Contacts contactCashe = null;

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
    contactCashe = null;
  }

  public void fillForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("middlename"), contactData.getMiddlename());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("home"), contactData.getPhoneNumber());
    type(By.name("mobile"), contactData.getMobileNumber());
    type(By.name("work"), contactData.getWorkNumber());
    type(By.name("fax"), contactData.getFax());
    type(By.name("email"), contactData.getEmail());
    type(By.name("email2"), contactData.getEmail2());
    type(By.name("email3"), contactData.getEmail3());
    type(By.name("nickname"), contactData.getNickname());
    type(By.name("company"), contactData.getCompany());
    type(By.name("title"), contactData.getTitle());
    type(By.name("homepage"), contactData.getHomepage());
    type(By.name("address2"), contactData.getSecAddress());
    type(By.name("phone2"), contactData.getSecHome());
//    attach(By.name("photo"), contactData.getPhoto());

    //check element availability on form on different types of operations on form: creation or modification
    if ( creation ) {
      if (contactData.getGroups().size() > 0) {
        //Assert.assertTrue(contactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroups().iterator().next().getName());
      }
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
  }

  public void initCreation() {
    click(By.linkText("add new"));
  }

  public void initModification(int index) {
    wd.findElements(By.cssSelector("img[alt=\"Edit\"]")).get(index).click();
  }

  public void initModificationById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(7).findElement(By.tagName("a")).click();

    //wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id)));
    //wd.findElement(By.xpath(String.format("//tr[.//input[@value='%s']]//td[8]/a", id)));
    //wd.findElement(By.xpath(String.format("a[href='edit.php?id=%s']", id)));
    //wd.findElement(By.xpath(String.format("a[href='edit.php?id=%s']", id))).click();
  }

  public void deleteSelected() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void select(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }

  public void selectById(int id) {
    wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
  }

  public void selectAll() {
    click(By.id("MassCB"));
  }

  public void submitModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
    contactCashe = null;
  }

  public void waitForAlertAndAccept() throws InterruptedException {
    Alert simpleAlert = wd.switchTo().alert();
//    simpleAlert.wait(5000);
    // Capturing alert message.
//    String alertMessage= simpleAlert.getText();
    //  Displaying alert message
//    System.out.println(alertMessage);
    simpleAlert.accept();
  }

  public void submitDeleteFromForm() {
    click(By.xpath("(//input[@name='update'])[3]"));
  }

  public void openDetails(int index) {
    wd.findElements(By.xpath("//img[@alt='Details']")).get(index).click();
  }

  public void openDetailsById(int id) {
    WebElement checkbox = wd.findElement(By.cssSelector(String.format("input[value='%s']", id)));
    WebElement row = checkbox.findElement(By.xpath("./../.."));
    List<WebElement> cells = row.findElements(By.tagName("td"));
    cells.get(6).findElement(By.tagName("a")).click();
  }

  public void initModificationFromDetails() {
    click(By.name("modifiy"));
  }

  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));
  }


  public void createContact(ContactData contactData, boolean b) {
    initCreation();
    fillForm(contactData, true);
    submitCreation();
    contactCashe = null;
  }

  public int count() {
    return wd.findElements(By.xpath("//img[@alt='Details']")).size();
  }

  public List<ContactData> list() {
    List<ContactData> contacts = new ArrayList<ContactData>();
    // gets all rows in table
//    WebElement mytable = wd.findElement(By.xpath("//*[@id=\"maintable\"]"));
    WebElement mytable = wd.findElement(By.cssSelector("#maintable"));
    //check all row, identification with 'tr' tag
    List<WebElement> rows = mytable.findElements(By.tagName("tr"));
    //row iteration, skip table header row
    for(int i=1; i<rows.size(); i++) {
      //check each column in row, use identification with 'td' tag
      List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
      //column iteration
//      System.out.println(cols.get(0).findElement(By.tagName("input")).getAttribute("value"));
//      for(int j=1; j<3; j++) {
//        System.out.println(cols.get(j).getText());
//      }
      int id = Integer.parseInt(cols.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cols.get(1).getText();
      String name = cols.get(2).getText();
      String address = cols.get(3).getText();
      ContactData contact = new ContactData().withId(id).withFirstname(name).withLastname(lastname).withAddress(address);
      contacts.add(contact);
    }
    return contacts;
  }


  public Contacts all() {
    if (contactCashe != null) {
      return new Contacts(contactCashe);
    }
    contactCashe = new Contacts();
    // gets all rows in table
//    WebElement mytable = wd.findElement(By.xpath("//*[@id=\"maintable\"]"));
    WebElement mytable = wd.findElement(By.cssSelector("#maintable"));
    //check all row, identification with 'tr' tag
    List<WebElement> rows = mytable.findElements(By.tagName("tr"));
    //row iteration, skip table header row
    for(int i=1; i<rows.size(); i++) {
      //check each column in row, use identification with 'td' tag
      List<WebElement> cols = rows.get(i).findElements(By.tagName("td"));
      //column iteration
//      System.out.println(cols.get(0).findElement(By.tagName("input")).getAttribute("value"));
//      for(int j=1; j<3; j++) {
//        System.out.println(cols.get(j).getText());
//      }
      int id = Integer.parseInt(cols.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String lastname = cols.get(1).getText();
      String name = cols.get(2).getText();
      String address = cols.get(3).getText();
      String allEmails = cols.get(4).getText();
      String allPhones = cols.get(5).getText();
      ContactData contact = new ContactData().
          withId(id).
          withFirstname(name).
          withLastname(lastname).
          withAddress(address).
          withAllPhones(allPhones).
          withAllEmails(allEmails).
          withAddress(address);
      contactCashe.add(contact);
    }
    return new Contacts(contactCashe);
  }

  public void delete() throws InterruptedException {
    deleteSelected();
    contactCashe = null;
    Thread.sleep(3000);
    waitForAlertAndAccept();
  }

  public void deleteFromForm(int id) {
    initModificationById(id);
    submitDeleteFromForm();
    contactCashe = null;
  }

  public void deleteAll() throws InterruptedException {
    selectAll();
    deleteSelected();
    contactCashe = null;
    Thread.sleep(3000);
    waitForAlertAndAccept();
  }

  public void modify(ContactData contact, boolean b) {
    fillForm(contact, false);
    submitModification();
    contactCashe = null;
  }

  public ContactData infoFromEditForm(ContactData contact) {
    initModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String middlename = wd.findElement(By.name("middlenam")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");
    String company = wd.findElement(By.name("company")).getAttribute("value");
    String title = wd.findElement(By.name("title")).getAttribute("value");
    String nickname = wd.findElement(By.name("nickname")).getAttribute("value");
    String phone = wd.findElement(By.name("home")).getAttribute("value");
    String mobile = wd.findElement(By.name("mobile")).getAttribute("value");
    String work = wd.findElement(By.name("work")).getAttribute("value");
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    wd.navigate().back();
    return new ContactData()
        .withId(contact.getId())
        .withFirstname(firstname)
        .withMiddlename(middlename)
        .withLastname(lastname)
        .withPhoneNumber(phone)
        .withMobileNumber(mobile)
        .withWorkNumber(work)
        .withEmail(email)
        .withEmail2(email2)
        .withEmail3(email3)
        .withAddress(address)
        .withCompany(company)
        .withTitle(title)
        .withNickname(nickname);
  }

  public void selectGroupViewByVisibleName(GroupData group) {
//    GroupData group = contact.getGroups().iterator().next();
    new Select(wd.findElement(By.name("group"))).selectByVisibleText(group.getName());
//    System.out.println("group selected from drop down");
  }

  public void selectGroupByVisibleName(GroupData group) {
    new Select(wd.findElement(By.name("to_group"))).selectByVisibleText(group.getName());
  }

  public void removeFromGroup(ContactData contact) {
    selectById(contact.getId());
    wd.findElement(By.name("remove")).click();
  }

  public void addToGroup() {
    wd.findElement(By.name("add")).click();
  }
}
