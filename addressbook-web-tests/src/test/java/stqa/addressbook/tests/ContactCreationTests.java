package stqa.addressbook.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    String groupName = "testgroup";
    //check that group exists
    app.goTo().groupPage();
    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName(groupName));
    }
    else {
      //get any group name from the set
      groupName = app.group().getName();
    }
    File photo = new File("src/test/resources/stru.png");
    List<Object[]> list = new ArrayList<Object[]>();
    BufferedReader reader = new BufferedReader(new FileReader(
        new File("src/test/resources/contacts.csv")));
    String line = reader.readLine();
    while (line != null) {
      String[] split = line.split(";");
      list.add(new Object[]{new ContactData().
          withFirstname(split[0]).
          withLastname(split[1]).
          withAddress(split[2]).
          withEmail(split[3]).
          withPhoneNumber(split[4]).
          withGroup(groupName)
          //              .withPhoto(photo)
      });

      line = reader.readLine();
    }
    return list.iterator();
  }

  @Test(dataProvider = "validContacts")
  public void testContactCreation(ContactData contact) throws Exception {
    app.goTo().homepage();
    Contacts before = app.contact().all();
    app.contact().createContact(contact, true);
    app.goTo().homepage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.contact().all();
//    //check that groups amount is changed
//    assertThat(after.size(), equalTo(before.size() + 1));

//    //get MAX id of all contacts
//    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId() );
//    before.add(contact);

    //compare sets
    assertThat(after, equalTo(
        before.withAdded(contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId() ))));
  }

//  @Test
//  public void testCurrentDir() {
//    File currentDir = new File(".");
//    System.out.println(currentDir.getAbsolutePath());
//    File photo = new File("src/test/resources/stru.png");
//    System.out.println(photo.getAbsolutePath());
//    System.out.println(photo.exists());
//  }

}
