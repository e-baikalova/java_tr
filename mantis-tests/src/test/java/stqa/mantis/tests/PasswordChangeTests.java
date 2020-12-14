package stqa.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import stqa.mantis.appmanager.HttpSession;
import stqa.mantis.model.MailMessage;
import stqa.mantis.model.Users;
import stqa.mantis.model.UsersData;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase{

  @BeforeMethod
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void passwordChangeTest() throws IOException {
    String newPassword = "NewPassword123";
    app.users().loginAs("administrator", "root");
    app.users().openUserPage();
    //get user data from the table on screen
    Users users = app.users().all();
    //get any user, not administrator
    Set<UsersData> res = new HashSet<UsersData>();
    for (UsersData temp : users) {
      if ( ! temp.getUsername().equals("administrator")) {
        res.add(temp);
      }
    }
    UsersData selectedUser = res.iterator().next();
    String username = selectedUser.getUsername();
    String email = selectedUser.getEmail();
    app.users().resetUserPassword(username);
    List<MailMessage> mailMessages = app.mail().waitForMail(1, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    System.out.println(confirmationLink);
    app.users().passwordResetConfirmation(confirmationLink, username, newPassword);
    HttpSession session = app.newSession();
    assertTrue(session.login(username, newPassword));
    assertTrue(session.isLoggedInAs(username));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

  @AfterMethod(alwaysRun = true)
  public void stopMailServer() {
    app.mail().stop();
  }

}
