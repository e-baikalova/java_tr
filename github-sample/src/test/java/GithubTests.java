import org.testng.annotations.Test;
import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import java.io.IOException;

public class GithubTests {

  @Test
  public void testCommits() throws IOException {
    Github github = new RtGithub("a39dc7e1e29f64759c309b7d93d6982ec0df5425");
    RepoCommits commits = github.repos().get(new Coordinates.Simple("e-baikalova", "java_tr")).commits();
    for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
      System.out.println(new RepoCommit.Smart(commit).message());
    }
  }

}
