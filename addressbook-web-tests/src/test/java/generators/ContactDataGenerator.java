package generators;

import stqa.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {
  public static void main(String[] args) throws IOException {
    int count = Integer.parseInt(args[0]);  //contacts count
    File file = new File(args[1]);//file path

    List<ContactData> contacts = generateContacts(count);
    save(contacts, file);
  }

  private static void save(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    Writer writer = new FileWriter(file);
    for (ContactData contact : contacts) {
      writer.write(String.format("%s;%s;%s;%s;%s\n",
          contact.getFirstname(),
          contact.getLastname(),
          contact.getAddress(),
          contact.getEmail(),
          contact.getPhoneNumber()
          ));
    }
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++){
      contacts.add(new ContactData().
          withFirstname(String.format("name%s", i)).
          withLastname(String.format("lname%s", i)).
          withAddress(String.format("test address %s", i)).
          withEmail(String.format("test_%s@email.com", i)).
          withPhoneNumber(String.format("00000%s", i)));
    }
    return contacts;
  }
}
