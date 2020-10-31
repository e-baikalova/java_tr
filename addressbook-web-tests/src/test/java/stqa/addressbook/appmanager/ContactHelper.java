package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import stqa.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {
  private FirefoxDriver wd;

  public ContactHelper(FirefoxDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("home"), contactData.getPhone());
  }

  public void initContanctCreation() {
    click(By.linkText("add new"));
  }

  public void initContanctModification() {
    click(By.cssSelector("img[alt=\"Edit\"]"));
  }

  public void initSelectedContactDelete() {
    click(By.xpath("//input[@value='Delete']"));
  }

  public void selectFirstContact() {
    click(By.name("selected[]"));
  }

  public void selectAllContacts() {
    click(By.id("MassCB"));
  }

  public void submitContactModification() {
    click(By.xpath("(//input[@name='update'])[2]"));
  }

}
