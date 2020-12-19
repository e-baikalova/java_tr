package stqa.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;
import model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

  @Test
  public void testGetIssues() throws IOException {
    skipIfNotFixed(453);
    Set<Issue> allIssues = getIssues();
    Set<Issue> issue = getIssueById(454);
    System.out.println("111");
  }

  @Test
  public void testCreateIssue() throws IOException {
    skipIfNotFixed(454);
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("Test issue - #003").withDescription("new test issue");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  private int createIssue(Issue newIssue) throws IOException {
    String json = getExecutor().execute(Request.Post("https://bugify.stqa.ru/api/issues.json")
        .bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
            new BasicNameValuePair("description", newIssue.getDescription())))
        .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

}
