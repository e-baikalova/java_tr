package stqa.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase {

  public NavigationHelper(WebDriver wd) {
    super(wd);
  }

  public void groupPage() {

    //check which page is currently opened
    if ( (isElementPresent(By.tagName("h1"))) &&
        (wd.findElement(By.tagName("h1")).getText().equals("Groups")) &&
        (isElementPresent(By.name("new"))) ) {
      return;
    } else {
      click(By.linkText("groups"));
    }
  }

  public void homepage() {
    // check if Homepage is already displayed
    if (isElementPresent(By.id("maintable"))) {
      return;
    } else {
      click(By.linkText("home"));
    }
  }

//  public void returnToHomepage() {
//    if (isElementPresent(By.id("maintable"))) {
//      return;
//    } else {
//      click(By.linkText("home page"));
//    }
//  }

}
