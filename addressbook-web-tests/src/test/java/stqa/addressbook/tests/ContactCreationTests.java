package stqa.addressbook.tests;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import stqa.addressbook.model.ContactData;
import stqa.addressbook.model.Contacts;
import stqa.addressbook.model.GroupData;
import stqa.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactCreationTests extends TestBase {

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
  }

  @DataProvider
  public Iterator<Object[]> validContactsXml() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {
      String xml = "";
      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(ContactData.class);
      List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }

  @DataProvider
  public Iterator<Object[]> validContactsJson() throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.json")))) {
      String json = "";
      String line = reader.readLine();
      while (line != null) {
        json += line;
        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
      }.getType());
      return contacts.stream().map((c) -> new Object[]{c}).collect(Collectors.toList()).iterator();
    }
  }


  @DataProvider
  public Iterator<Object[]> validContactsCsv() throws IOException {
    Groups groups = app.db().groups();
    String groupName = "test 1";
    app.goTo().homepage();
    if (groups.size() == 0) {
      //check that group exists
      app.goTo().groupPage();
      app.group().create(new GroupData().withName(groupName));
    }
    File photo = new File("src/test/resources/stru.png");
    List<Object[]> list = new ArrayList<Object[]>();
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.csv")))) {
      String line = reader.readLine();
      while (line != null) {
        String[] split = line.split(";");
        list.add(new Object[]{new ContactData()
            .withFirstname(split[0])
            .withLastname(split[1])
            .withAddress(split[2])
            .withEmail(split[3])
            .withPhoneNumber(split[4])
            .withPhoto(photo)
            .inGroup(groups.iterator().next())});
        line = reader.readLine();
      }
      return list.iterator();
    }
  }

  @Test(dataProvider = "validContactsJson")
  public void testContactCreation(ContactData contact) throws Exception {
    Groups groups = app.db().groups();
    File photo = new File("src/test/resources/stru.png");
    app.goTo().homepage();
    Contacts before = app.db().contacts();
    app.contact().createContact(contact, true);
    app.goTo().homepage();
    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();
//    //check that groups amount is changed
//    assertThat(after.size(), equalTo(before.size() + 1));

//    //get MAX id of all contacts
//    contact.withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId() );
//    before.add(contact);

    //compare sets
    assertThat(after, equalTo(
        before.withAdded(contact
            .withId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(),o2.getId())).get().getId() )
            )));
    verifyContactsListInUi();
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
