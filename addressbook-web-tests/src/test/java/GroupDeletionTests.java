import org.testng.annotations.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion() throws Exception {
    goToGroups();
    selectGroup();
    deleteSelectedGroups();
    returnToGroups();
  }

}
