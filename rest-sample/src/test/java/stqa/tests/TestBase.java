package stqa.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import model.Issue;

import java.io.IOException;
import java.util.Set;

public class TestBase {

  static Executor getExecutor() {
    return Executor.newInstance().auth("288f44776e7bec4bf44fdfeb1e646490", ""); //bugify.stqa.ru/api
  }

  Set<Issue> getIssues() throws IOException {
    String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues.json"))
        .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  public static Set<Issue> getIssueById(int issueId) throws IOException {
    String json = getExecutor()
        .execute(Request.Get(String.format("https://bugify.stqa.ru/api/issues/%s.json", issueId)))
        .returnContent().asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>(){}.getType());
  }

  public boolean isIssueOpen(int issueId) throws IOException {
    Set<Issue> issue = getIssueById(453);
    if (issue.iterator().next().getState_name().equals("Resolved") || issue.iterator().next().getState_name().equals("Closed")) {
      return false;
    }
    else {
      return true;
    }
  }

  public void skipIfNotFixed(int issueId) throws IOException {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
