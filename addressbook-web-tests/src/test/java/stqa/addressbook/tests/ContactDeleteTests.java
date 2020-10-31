package stqa.addressbook.tests;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class ContactDeleteTests extends TestBase {

  @Test
  public void testSingleContactDelete() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().selectFirstContact();
    app.getContactHelper().initSelectedContactDelete();
    Thread.sleep(5000);
    // Switching to Alert
    Alert simpleAlert = wd.switchTo().alert();
//    simpleAlert.wait(5000);
//    wd.switchTo().alert().accept();

    // Capturing alert message.
//    String alertMessage= simpleAlert.getText();
////
//    //  Displaying alert message
//    System.out.println(alertMessage);
//    Thread.sleep(5000);

    // Accepting alert
    simpleAlert.accept();
//    app.getNavigationHelper().returnToHomepage();
//    app.getSessionHelper().logout();
  }

}
