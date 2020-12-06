package stqa.addressbook.tests;

import com.google.common.collect.Sets;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
      //check that at least one group exists
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(groupName));
    }
    if (contacts.size() == 0) {
      //check that at least one contact exists
      app.contact().createContact(new ContactData()
              .withFirstname("test_name2")
              .withLastname("test_lname")
              .withAddress("test address")
              .withEmail("test@email.com")
              .withPhoneNumber("1263547")
              .inGroup(groups.iterator().next()),
          true);
    }
    //ensure at least one contact with unassigned group exists
    Contacts beforeContacts = app.db().contacts();
    ContactData selectedContact;
    Set<ContactData> res = new HashSet<ContactData>();
    for (ContactData temp : beforeContacts) {
      if (temp.getGroups().size() > 0) {
        res.add(temp);
      }
    }
    if (res.size() == 0) {
      //all groups are unassigned for each contact, new group will be assigned to contact
      //select any contact for test
      selectedContact = beforeContacts.iterator().next();
      //search for any group not assigned to contact
      Set<GroupData> availableGroups = Sets.symmetricDifference(groups, selectedContact.getGroups());
      //add contact to selected group
      GroupData selectedGroup = availableGroups.iterator().next();
      app.contact().selectById(selectedContact.getId());
      app.contact().selectGroupByVisibleName(selectedGroup);
      app.contact().addToGroup();
      beforeContacts = app.db().contacts();
      //update selected contact groups
      for (ContactData temp : beforeContacts) {
        if (temp.getId() == selectedContact.getId()) {
          selectedContact = temp;
        }
      }
    }
  }

  @Test
  public void testRemoveFromGroup() {
    app.goTo().homepage();
    Contacts beforeContacts = app.db().contacts();
    Groups groups = app.db().groups();
    //find all contacts with at least one group assigned
    Set<ContactData> res = new HashSet<ContactData>();
    for (ContactData temp : beforeContacts) {
      if (temp.getGroups().size() > 0 ) {
        res.add(temp);
      }
    }
    //get any contact from res set
    ContactData selectedContact = res.iterator().next();
    //search for any group not assigned to contact
    Set<GroupData> availableGroups = Sets.symmetricDifference(groups, selectedContact.getGroups());
    //remove contact from selected group
    GroupData group = selectedContact.getGroups().iterator().next();
    //go to required groups view
    app.contact().selectGroupViewByVisibleName(group);
    //remove contact from group
    app.contact().removeFromGroup(selectedContact);
    Contacts afterContacts = app.db().contacts();
    Set<ContactData> unchangedContacts = beforeContacts.stream().filter(c -> afterContacts.contains(c)).collect(Collectors.toSet());
    assertThat(unchangedContacts.size(), equalTo(beforeContacts.size() - 1));
    //check that amount of groups assigned to selected contact is changed
    Set<ContactData> modifiedContact = afterContacts.stream().filter(c -> !unchangedContacts.contains(c)).collect(Collectors.toSet());
    assertThat(selectedContact.getGroups().size(), equalTo(modifiedContact.iterator().next().getGroups().size() + 1));
  }
}
