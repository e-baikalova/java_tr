package stqa;

import org.testng.SkipException;

public class TestBase {

  public boolean isIssueOpen(int issueId) {
    return true;
//    if (status.equals("resolved") || status.equals("closed")) {
//      return false;
//    }
//    else {
//      return true;
//    }
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
