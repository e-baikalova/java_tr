package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homepage();
    if (app.contact().list().size() == 0) {
      String groupName = "testgroup_1";
      //check that group exists
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName(groupName));
      }
      else {
        //return any group name from the set
        groupName = app.group().getName();
      }
      app.contact().createContact(new ContactData().
              withFirstname("test_name1").
              withLastname("test_lname").
              withAddress("test address").
              withEmail("test@email.com").
              withPhone("1263547").
              withGroup(groupName),
          true);
    }
  }

  @Test
  public void testContactModificationFromContactsView() throws Exception {
    app.goTo().homepage();
    Contacts before = app.contact().all();
    int index = before.size()-1;
    app.contact().initModification(index);
    //we save original id for record
    ContactData modifiedContact = before.iterator().next();
    ContactData contact = new ContactData().
        withId(modifiedContact.getId()).
        withFirstname("test_name2").
        withLastname("test_lname").
        withAddress("test address").
        withEmail("test@email.com").
        withPhone("1263547");
    app.contact().fillForm(contact, false);
    app.contact().submitModification();
    app.goTo().homepage();
    Contacts after = app.contact().all();
    //check that groups amount is not changed
    assertThat(after.size(), equalTo(before.size()));
    assertThat(after, equalTo(before.without(modifiedContact).withAdded(contact)));
  }

  @Test(enabled = false)
  public void testContactModificationFromContactDetails() throws Exception {
    app.goTo().homepage();
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    //we save original id for record
    ContactData contact = new ContactData().
        withId(before.get(index).getId()).
        withFirstname("test_name2").
        withFirstname("test_name3").
        withLastname("test_lname").
        withAddress("test address").
        withEmail("test@email.com").
        withPhone("1263547");
    app.contact().openDetails(index);
    //open first element on form
    app.contact().initModificationFromDetails();
    app.contact().fillForm(contact, false);
    app.contact().submitModification();
    app.goTo().homepage();
    List<ContactData> after = app.contact().list();
    //check that groups amount is not changed
    Assert.assertEquals(after.size(), before.size());
    //check 2 lists
    before.remove(index);
    before.add(contact);

    //list sorting
    Comparator<? super ContactData> byId = (c1, c2) -> {
      return Integer.compare(c1.getId(), c2.getId());
    };
    before.sort(byId);
    after.sort(byId);

    //lists comparison
    Assert.assertEquals(before, after);
  }

}
