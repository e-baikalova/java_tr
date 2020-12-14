package stqa.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import stqa.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase{

//  @BeforeMethod //should be disable for james, enabled for mailserver inside tests
  public void startMailServer() {
    app.mail().start();
  }

  @Test
  public void testRegistration() throws IOException, MessagingException {
    long now = System.currentTimeMillis();
    String username = "user_" + now;
    String password = "password";
//    String email = String.format("%s@localhost.localdomain", username); //used for mailserver in tests
    String email = String.format("%s@localhost", username);
    app.james().createUser(username, password);
    app.registration().start(username, email);
//    List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000); //used for mailserver in tests
    List<MailMessage> mailMessages = app.james().waitForMail(username, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, password);
    assertTrue(app.newSession().login(username, password));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findAny().get();
    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }

//  @AfterMethod(alwaysRun = true) //should be disable for james, enabled for mailserver inside tests
  public void stopMailServer() {
    app.mail().stop();
  }
}
