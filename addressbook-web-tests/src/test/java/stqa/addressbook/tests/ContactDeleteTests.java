package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.GroupData;

import java.util.List;

public class ContactDeleteTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().homepage();
    if (app.contact().list().size() == 0) {
      String groupName = "testgroup3";
      //check that group exists
      app.goTo().groupPage();
      if (app.group().list().size() == 0) {
        app.group().create(new GroupData().withName(groupName));
      }
      else {
        groupName = app.group().getName(0);
      }
      app.contact().createContact(new ContactData().
          withFirstname("test_name2").
          withLastname("test_lname").
          withAddress("test address").
          withEmail("test@email.com").
          withPhone("1263547").
          withGroup(groupName),
          true);
    }
  }

  @Test
  public void testSingleSelectedContactDelete() throws Exception {
    app.goTo().homepage();
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    app.contact().select(index);
    app.contact().deleteSelected();
    app.contact().waitForAlertAndAccept();
    app.goTo().homepage();
    Thread.sleep(5000);
    List<ContactData> after = app.contact().list();
    //check that contact amount is changed
    Assert.assertEquals(after.size(), index);
//    Thread.sleep(5000);
    //we remove the contact from the original list to check that required contact was deleted
    before.remove(index);
//    Thread.sleep(5000);
    //compare 2 lists: original list and modified list
//    for (int i=0; i<after.size(); i++){
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    Assert.assertEquals(before, after);

  }

  @Test
  public void testContactDeleteFromForm() throws Exception {
    app.goTo().homepage();
    List<ContactData> before = app.contact().list();
    int index = before.size()-1;
    app.contact().initModification(index);
    app.contact().submitDeleteFromForm();
    app.goTo().homepage();
    Thread.sleep(5000);
    List<ContactData> after = app.contact().list();
    //check that contact amount is changed
    Assert.assertEquals(after.size(), index);
    Thread.sleep(5000);
    //we remove the contact from the original list to check that required contact was deleted
    before.remove(index);

    //compare 2 lists: original list and modified list
//    for (int i=0; i<after.size(); i++){
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    Assert.assertEquals(before, after);
  }

  @Test
  public void testAllSelectedContactsDelete() throws Exception {
    app.goTo().homepage();
    List<ContactData> before = app.contact().list();
    for (int i = 0; i < before.size(); i++){
      //we remove the contact from the original list to empty the list
      before.remove(i);
    }
    app.contact().selectAll();
    app.contact().deleteSelected();
    app.contact().waitForAlertAndAccept();
    app.goTo().homepage();
    Thread.sleep(5000);
    List<ContactData> after = app.contact().list();
    //check that groups contact is changed
    Assert.assertEquals(after.size(), before.size());
    Thread.sleep(5000);
    //compare 2 lists: original list and modified list
//    for (int i=0; i<after.size(); i++){
//      Assert.assertEquals(before.get(i), after.get(i));
//    }
    Assert.assertEquals(before, after);
  }

}
