package stqa.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactDeleteTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homepage();
    if (app.contact().all().size() == 0) {
      String groupName = "testgroup3";
      //check that group exists
      app.goTo().groupPage();
      if (app.group().all().size() == 0) {
        app.group().create(new GroupData().withName(groupName));
      }
      else {
        groupName = app.group().getName();
      }
      app.contact().createContact(new ContactData().
          withFirstname("test_name2").
          withLastname("test_lname").
          withAddress("test address").
          withEmail("test@email.com").
              withPhoneNumber("1263547").
          withGroup(groupName),
          true);
    }
  }

  @Test
  public void testSingleSelectedContactDelete() throws Exception {
    app.goTo().homepage();
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().selectById(deletedContact.getId());
    app.contact().delete();
    app.goTo().homepage();
    Thread.sleep(5000);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    Contacts after = app.contact().all();
//    //check that contact amount is changed
//    assertThat(after.size(), equalTo(before.size()-1));
//    Thread.sleep(5000);
    //we remove the contact from the original list to check that required contact was deleted
//    Thread.sleep(5000);
    //compare 2 lists: original list and modified list
//    for (int i=0; i<after.size(); i++){
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    assertThat(after, equalTo(before.without(deletedContact)));

  }

  @Test(enabled = false)
  public void testContactDeleteFromForm() throws Exception {
    app.goTo().homepage();
    Contacts before = app.contact().all();
    ContactData deletedContact = before.iterator().next();
    app.contact().deleteFromForm(deletedContact.getId());
    app.goTo().homepage();
    Thread.sleep(5000);
    assertThat(app.contact().count(), equalTo(before.size() - 1));
    List<ContactData> after = app.contact().list();
//    //check that contact amount is changed
//    Assert.assertEquals(after.size(), before.size()-1);
    Thread.sleep(5000);
    //we remove the contact from the original list to check that required contact was deleted

    //compare 2 lists: original list and modified list
//    for (int i=0; i<after.size(); i++){
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    assertThat(after, equalTo(before.without(deletedContact)));
  }

  @Test(enabled = false)
  public void testAllSelectedContactsDelete() throws Exception {
    app.goTo().homepage();
    Contacts before = app.contact().all();
    before.clear();
//    for (int i = 0; i < before.size(); i++){
//      //we remove the contact from the original list to empty the list
//      before.remove(i);
//    }
    app.contact().deleteAll();
    app.goTo().homepage();
    Thread.sleep(5000);
    assertThat(app.contact().count(), equalTo(before.size()));
    Contacts after = app.contact().all();
//    //check that groups contact is changed
//    Assert.assertEquals(after.size(), before.size());
    Thread.sleep(5000);
    //compare 2 lists: original list and modified list
//    for (int i=0; i<after.size(); i++){
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    assertThat(after, equalTo(before));
  }

}
