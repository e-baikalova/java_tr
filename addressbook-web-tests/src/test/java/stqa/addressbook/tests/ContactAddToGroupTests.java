package stqa.addressbook.tests;

import com.google.common.collect.Sets;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactAddToGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    String groupName = "test 10";
    app.goTo().homepage();
    if (groups.size() == 0) {
      //check that group exists
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(groupName));
    }
    if (contacts.size() == 0) {
      app.contact().createContact(new ContactData()
              .withFirstname("test_name2")
              .withLastname("test_lname")
              .withAddress("test address")
              .withEmail("test@email.com")
              .withPhoneNumber("1263547")
              .inGroup(groups.iterator().next()),
          true);
    }
  }

  @Test
  public void testAddToGroup() {
    app.goTo().homepage();
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    String groupName = "test group";
    int iteration = 0;
    ContactData modifiedContact = before.iterator().next();
    int beforeGroupsCount = modifiedContact.getGroups().size();
    //search for contact with not all groups assigned to it
    while (iteration < 5) {
      System.out.println("iteration: " + iteration + " selected contact = " + modifiedContact.getFirstname() +
          " " + modifiedContact.getLastname() + ", contact group = " + modifiedContact.getGroups());
      if (beforeGroupsCount != groups.size()) {
        iteration = 11;
      } else {
        iteration++;
        before.iterator().next();
      }
    }
    //check that we found required contact within our search
    if (beforeGroupsCount == groups.size()) {
      System.out.println("required contact wasn't found within specified amount of tries: " + iteration);
//      //create new group
      app.goTo().groupPage();
      System.out.println("new group " + groupName + " is created");
      app.group().create(new GroupData().withName(groupName));
      groups = app.db().groups();
      app.goTo().homepage();
    }
    //search for any group not assigned to contact
    Set<GroupData> availableGroups = Sets.symmetricDifference(groups, modifiedContact.getGroups());
    System.out.println("available groups: " + availableGroups);
    //add contact to selected group
    GroupData selectedGroup = availableGroups.iterator().next();
    System.out.println("group " + selectedGroup.getName() + " will be used in tests");
    app.contact().selectById(modifiedContact.getId());
    app.contact().selectGroupByVisibleName(selectedGroup);
    //add contact to group
    app.contact().addToGroup();
    modifiedContact.inGroup(selectedGroup);
    //check groups assigned to contact
    assertThat(modifiedContact.getGroups().size(), equalTo(beforeGroupsCount + 1));
    System.out.println("selected contact = " + modifiedContact.getFirstname() +
        " " + modifiedContact.getLastname() + ", new contact groups = " + modifiedContact.getGroups());
    assert(modifiedContact.getGroups().contains(selectedGroup));
  }
}
