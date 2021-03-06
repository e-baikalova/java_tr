//-c 3 -d json -f C:\Training\java_tr\addressbook-web-tests\src\test\resources\contacts.json

package generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import stqa.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

  @Parameter(names = "-c", description = "Contacts Count")
  public int count;

  @Parameter(names = "-f", description = "Target File")
  public String file;

  @Parameter(names = "-d", description = "Data Format")
  public String format;


  public static void main(String[] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

  private void run() throws IOException {
    List<ContactData> contacts = generateContacts(count);
    if (format.equals("csv")) {
      saveAsCsv(contacts, new File(file));
    } else if (format.equals("xml")) {
      saveAsXml(contacts, new File(file));
    } else if (format.equals("json")) {
      saveAsJson(contacts, new File(file));
    } else {
      System.out.println("data format " + format + " is not supported");
    }
  }

  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    System.out.println(new File(".").getAbsolutePath());
    try (Writer writer = new FileWriter(file)) {
      for (ContactData contact : contacts) {
        writer.write(String.format("%s;%s;%s;%s;%s\n",
            contact.getFirstname(),
            contact.getLastname(),
            contact.getAddress(),
            contact.getEmail(),
            contact.getPhoneNumber()
        ));
      }
    }
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(json);
    }
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    try (Writer writer = new FileWriter(file)) {
      writer.write(xml);
    }
  }

  private List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<ContactData>();
    for (int i = 0; i < count; i++){
      contacts.add(new ContactData()
          .withId(100 + i)
          .withFirstname(String.format("name%s", i))
          .withLastname(String.format("lname%s", i))
          .withAddress(String.format("test address %s", i))
          .withEmail(String.format("test_10%s@email.com", i))
          .withEmail2(String.format("test_20%s@email.com", i))
          .withEmail3(String.format("test_30%s@email.com", i))
          .withPhoneNumber(String.format("100000%s", i))
          .withMobileNumber(String.format("200(00)0%s", i))
          .withWorkNumber(String.format("300-00-0%s", i))
//          .withGroup("test 0")
      );
    }
    return contacts;
  }
}
