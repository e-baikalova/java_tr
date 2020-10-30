import org.testng.annotations.*;

public class GroupCreationTests extends TestBase {

  @Test
  public void testGroupCreation() throws Exception {
    goToGroups();
    initGroupCreation();
    fillGroupForm(new GroupData("test1222", "test2", "test3"));
    submitGroupCreation();
    returnToGroups();
    logout();
  }

}
