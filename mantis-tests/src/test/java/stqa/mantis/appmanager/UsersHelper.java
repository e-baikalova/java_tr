package stqa.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import stqa.mantis.model.Users;
import stqa.mantis.model.UsersData;

import java.util.List;

public class UsersHelper extends HelperBase {

  private Users usersCashe = null;

  public UsersHelper(ApplicationManager app) {
    super(app);
  }

  public void loginAs(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login.php");
    type(By.name("username"), username);
    click(By.xpath("//form[@id='login-form']/fieldset/input[2]"));
    type(By.name("password"), password);
    click(By.cssSelector("input.width-40.pull-right.btn.btn-success.btn-inverse.bigger-110"));
  }

  public void openUserPage() {
      click(By.cssSelector("i.menu-icon.fa.fa-gears"));
      click(By.xpath("//a[contains(@href, '/mantisbt-2.24.2/manage_user_page.php')]"));
  }

  public void resetUserPassword(String username) {
    click(By.xpath(String.format("//a[contains(text(),'%s')]", username)));
    click(By.cssSelector("#manage-user-reset-form > fieldset:nth-child(1) > span:nth-child(3) > input:nth-child(1)"));
  }

  public void passwordResetConfirmation(String confirmationLink, String username, String password) {
    wd.get(confirmationLink);
    type(By.name("realname"), username);
    type(By.name("password"), password);
    type(By.name("password_confirm"), password);
    click(By.cssSelector("#account-update-form > fieldset > span > button > span"));
  }

  public Users all() {
    if (usersCashe != null) {
      return new Users(usersCashe);
    }
    usersCashe = new Users();
    // gets all rows in table
//    WebElement mytable = wd.findElement(By.xpath("//*[@id=\"maintable\"]"));
    WebElement mytable = wd.findElement(By.cssSelector("#main-container > div.main-content > div.page-content > div > div > div.widget-box.widget-color-blue2 > div.widget-body > div.widget-main.no-padding > div > table"));
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
      //int id = Integer.parseInt(cols.get(0).findElement(By.tagName("input")).getAttribute("value"));
      String username = cols.get(0).getText();
      String email = cols.get(2).getText();
      UsersData user = new UsersData().
          //withId(id).
          withUsername(username).
          withEmail(email);
      usersCashe.add(user);
    }
    return new Users(usersCashe);
  }


}
