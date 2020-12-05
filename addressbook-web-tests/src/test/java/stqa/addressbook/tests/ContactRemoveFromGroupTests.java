package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactRemoveFromGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    Groups groups = app.db().groups();
    Contacts contacts = app.db().contacts();
    String groupName = "test 1";
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
  public void testRemoveFromGroup() {
    app.goTo().homepage();
    Contacts before = app.db().contacts();
    int iteration = 0;
    ContactData modifiedContact = before.iterator().next();
    int beforeGroupsCount = modifiedContact.getGroups().size();
    //search for contact with at least one group unassigned
    while (iteration < 5) {
//      System.out.println("iteration: " + iteration + " selected contact = " + modifiedContact.getFirstname() +
//          " " + modifiedContact.getLastname() + ", contact group = " + modifiedContact.getGroups());
      if (beforeGroupsCount != 0) {
        iteration = 11;
      }
      else {
        iteration++;
        before.iterator().next();
      }
    }
    //check that we found required contact within our test
    if (beforeGroupsCount != 0) {
      GroupData group = modifiedContact.getGroups().iterator().next();
      //go to required groups view
      app.contact().selectGroupViewByVisibleName(group);
      //remove contact from group
      app.contact().removeFromGroup(modifiedContact);
      modifiedContact.outOfGroup(group);
      //check groups assigned to contact
      assertThat(modifiedContact.getGroups().size(), equalTo(beforeGroupsCount - 1) );
//      System.out.println("selected contact = " + modifiedContact.getFirstname() +
//          " " + modifiedContact.getLastname() + ", new contact groups = " + modifiedContact.getGroups());
      assert(!modifiedContact.getGroups().contains(group));
    }
    else {
      System.out.println("required contact wasn't found within specified amount of tries: " + iteration);
    }
  }
}
