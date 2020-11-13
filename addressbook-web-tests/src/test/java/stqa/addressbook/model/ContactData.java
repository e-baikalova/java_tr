package stqa.addressbook.model;

import java.sql.Date;

public class ContactData {
  private int id;
  private final String firstname;
  private final String middlename = null;
  private final String lastname;
  private final String address;
  private final String nickname = null;
  private final String company = null;
  private final String title = null;
  private final String phone;
  private String group;
  private final String mobileNumber = null;
  private final String workNumber = null;
  private final String fax = null;
  private final String email;
  private final String email2 = null;
  private final String email3 = null;
  private final String homepage = null;
  private final Date birthsday = null;
  private final Date anniversary = null;
  private final String secAddress = null;
  private final String secHome = null;
  private final String notes = null;

  public ContactData(String firstname, String lastname, String address, String email, String phone, String group) {
    this.id = Integer.MAX_VALUE;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.phone = phone;
    this.group = group;
  }

  public ContactData(int id, String firstname, String lastname, String address, String email, String phone, String group) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
    this.address = address;
    this.email = email;
    this.phone = phone;
    this.group = group;
  }

  @Override
  public String toString() {
    return "ContactData{" +
        "id=" + id +
        ", firstname='" + firstname + '\'' +
        ", lastname='" + lastname + '\'' +
        '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (id != that.id) return false;
    if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
    return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
    result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
    return result;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getLastname() {
    return lastname;
  }

  public String getAddress() {
    return address;
  }

  public String getEmail() {
    return email;
  }

  public String getPhone() {
    return phone;
  }

  public String getFirstname() {
    return firstname;
  }

  public String getMiddlename() {
    return middlename;
  }

  public String getNickname() {
    return nickname;
  }

  public String getCompany() {
    return company;
  }

  public String getTitle() {
    return title;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public String getWorkNumber() {
    return workNumber;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }

  public String getHomepage() {
    return homepage;
  }

  public Date getBirthsday() {
    return birthsday;
  }

  public Date getAnniversary() {
    return anniversary;
  }

  public String getSecAddress() {
    return secAddress;
  }

  public String getSecHome() {
    return secHome;
  }

  public String getNotes() {
    return notes;
  }

  public String getGroup() {
    return group;
  }
}
