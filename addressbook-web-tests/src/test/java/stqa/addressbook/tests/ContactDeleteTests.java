package stqa.addressbook.tests;

import org.testng.annotations.*;

public class ContactDeleteTests extends TestBase {

  @Test
  public void testSingleContactDelete() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    app.getContactHelper().selectFirstContact();
    app.getContactHelper().initSelectedContactDelete();
    Thread.sleep(5000);
    // Switching to Alert
//    Alert simpleAlert = wd.switchTo().alert();
//    simpleAlert.accept();
//    simpleAlert.wait(5000);
    wd.switchTo().alert().accept();

    // Capturing alert message.
//    String alertMessage= wd.switchTo().alert().getText();
////
//    //  Displaying alert message
//    System.out.println(alertMessage);
//    Thread.sleep(5000);

    // Accepting alert
//    simpleAlert.accept();
//    app.getNavigationHelper().returnToHomepage();
//    app.getSessionHelper().logout();
  }

}
