package stqa.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class ContactModificationTests extends TestBase {

  @Test
  public void testContactModificationFromContactsForm() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name", "test_lname", "test address",
          "test@email.com", "1263547","testgroup"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().initContanctModification(before.size()-1);
    //we save original id for record
    ContactData contact = new ContactData(before.get(before.size()-1).getId(), "mod_test_name", "test_lname", "test address",
        "test@email.com", "1263547", "testgroup");
    app.getContactHelper().fillContactForm(contact, false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> after = app.getContactHelper().getContactList();
    //check that groups amount is not changed
    Assert.assertEquals(after.size(), before.size());

    //check 2 lists
    before.remove(before.size() - 1);
    before.add(contact);

    //list sorting
    Comparator<? super ContactData> byId = (c1, c2) -> Integer.compare(c1.getId(), c2.getId());
    before.sort(byId);
    after.sort(byId);

    //lists comparison
    Assert.assertEquals(before, after);
  }

  @Test(enabled = false)
  public void testContactModificationFromContactDetails() throws Exception {
    app.getNavigationHelper().gotoHomepage();
    if (! app.getContactHelper().isThereAContact()) {
      app.getContactHelper().createContact(new ContactData("test_name_2", "test_lname", "test address",
          "test@email.com", "1263547", "testgroup"), true);
    }
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> before = app.getContactHelper().getContactList();
    app.getContactHelper().openContactDetails(before.size()-1);
    app.getContactHelper().initContanctModificationFromDetails();
    app.getContactHelper().fillContactForm(new ContactData("mod_test_name2", "test_lname_555",
        "test address", "test@email.com", "1263547", "testgroup"), false);
    app.getContactHelper().submitContactModification();
    app.getNavigationHelper().gotoHomepage();
    List<ContactData> after = app.getContactHelper().getContactList();
    //check that groups amount is not changed
    Assert.assertEquals(after.size(), before.size());
  }

}
