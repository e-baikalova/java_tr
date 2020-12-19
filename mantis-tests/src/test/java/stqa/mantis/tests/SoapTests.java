package stqa.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectLocator;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import biz.futureware.mantis.rpc.soap.client.ProjectData;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.mantis.appmanager.SoapHelper;
import stqa.mantis.model.Issue;
import stqa.mantis.model.Project;

import javax.xml.rpc.ServiceException;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class SoapTests extends TestBase{

  @Test
  public void GetProjects() throws MalformedURLException, ServiceException, RemoteException {
    //following code is moved to Project class
//    MantisConnectPortType mc = new MantisConnectLocator()
//        .getMantisConnectPort(new URL("http://localhost/mantisbt-2.24.2/api/soap/mantisconnect.php"));
//    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
    skipIfNotFixed(2);
    Set<Project> projects = app.soap().getProjects();
    System.out.println("amount of projets: " + projects.size());
    for (Project project : projects) {
      System.out.println("projet name: " + project.getName());
    }
  }


  @Test
  public void TestCreateIssue() throws RemoteException, ServiceException, MalformedURLException {
    skipIfNotFixed(1);
    Set<Project> projects = app.soap().getProjects();
    Issue issue = new Issue().withSummary("Test issue")
        .withDescription("Test issue description").withProject(projects.iterator().next());
    Issue created = app.soap().addIssue(issue);
    assertEquals(issue.getSummary(), created.getSummary());
  }

  @Test
  public void TestIssueStatus() throws RemoteException, MalformedURLException, ServiceException {
    skipIfNotFixed(1);
//    MantisConnectPortType mc = new MantisConnectLocator()
//        .getMantisConnectPort(new URL("http://localhost/mantisbt-2.24.2/api/soap/mantisconnect.php"));
//    ProjectData[] projects = mc.mc_projects_get_user_accessible("administrator", "root");
//    BigInteger issueId = mc.mc_issue_get_id_from_summary("administrator", "root", "Test issue1");
//    System.out.println(issueId);
//    IssueData issue = mc.mc_issue_get("administrator", "root", issueId);
//    System.out.println(issue.getStatus().getName());
    System.out.println(isIssueOpen(2));
  }
}
