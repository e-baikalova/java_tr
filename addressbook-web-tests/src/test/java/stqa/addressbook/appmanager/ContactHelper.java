package stqa.addressbook.appmanager;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import stqa.addressbook.model.ContactData;

public class ContactHelper extends HelperBase {

  public ContactHelper(WebDriver wd) {
    super(wd);
  }

  public void submitContactCreation() {
    click(By.xpath("(//input[@name='submit'])[2]"));
  }

  public void fillContactForm(ContactData contactData, boolean creation) {
    type(By.name("firstname"), contactData.getFirstname());
    type(By.name("lastname"), contactData.getLastname());
    type(By.name("address"), contactData.getAddress());
    type(By.name("email"), contactData.getEmail());
    type(By.name("home"), contactData.getPhone());

    //check element availability on form on different types of operations on form: creation or modification
    if ( creation ) {
      new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
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

  public void waitForAlertAndAccept() throws InterruptedException {
    Alert simpleAlert = wd.switchTo().alert();
//    simpleAlert.wait(5000);
    // Capturing alert message.
//    String alertMessage= simpleAlert.getText();
    //  Displaying alert message
//    System.out.println(alertMessage);
    simpleAlert.accept();
  }

  public void submitContactDeleteFromForm() {
    click(By.xpath("(//input[@name='update'])[3]"));
  }

  public void openContactDetails() {
    click(By.xpath("//img[@alt='Details']"));
  }

  public void initContanctModificationFromDetails() {
    click(By.name("modifiy"));
  }
}
