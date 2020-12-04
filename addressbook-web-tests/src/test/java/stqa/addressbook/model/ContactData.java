package stqa.addressbook.model;

import com.google.gson.annotations.Expose;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.sql.Date;

@XStreamAlias("contact")
@Entity
@Table(name = "addressbook")
public class ContactData {
  @Expose
  @Id
  @Column(name = "id")
  private int id = Integer.MAX_VALUE;
  @Expose
  @Column(name = "firstname")
  private String firstname;
  @Column(name = "middlename")
  private String middlename;
  @Expose
  @Column(name = "lastname")
  private String lastname;
  @Expose
  @Type(type = "text")
  @Column(name = "address")
  private String address = null;
  @Column(name = "nickname")
  private String nickname = null;
  @Column(name = "company")
  private String company = null;
  @Column(name = "title")
  private String title = null;
  @Expose
  @Type(type = "text")
  @Column(name = "home")
  private String phone;
  @Expose
  @Transient
  private String group;
  @Expose
  @Column(name = "mobile")
  @Type(type = "text")
  private String mobileNumber;
  @Expose
  @Column(name = "work")
  @Type(type = "text")
  private String workNumber;
  @Column(name = "fax")
  @Type(type = "text")
  private String fax;
  @Expose
  @Type(type = "text")
  @Column(name = "email")
  private String email;
  @Expose
  @Column(name = "email2")
  @Type(type = "text")
  private String email2;
  @Expose
  @Column(name = "email3")
  @Type(type = "text")
  private String email3;
  @Column(name = "homepage")
  @Type(type = "text")
  private String homepage = null;
  @Transient
  private Date birthsday;
  @Transient
  private Date anniversary;
  @Column(name = "address2")
  @Type(type = "text")
  private String secAddress = null;
  @Column(name = "phone2")
  @Type(type = "text")
  private String secHome = null;
  @Column(name = "notes")
  @Type(type = "text")
  private String notes = null;
  @Transient
  private String allPhones;
  @Transient
  private String allEmails;
  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

  public String getAllPhones() {
    return allPhones;
  }

  public String getAllEmails() {
    return allEmails;
  }

  public ContactData withAllEmails(String allEmails) {
    this.allEmails = allEmails;
    return this;
  }

  public ContactData withAllPhones(String allPhones) {
    this.allPhones = allPhones;
    return this;
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

  @Override
  public String toString() {
    return "ContactData{" +
        "id=" + id +
        ", firstname='" + firstname + '\'' +
        ", middlename='" + middlename + '\'' +
        ", lastname='" + lastname + '\'' +
        ", company='" + company + '\'' +
        ", phone='" + phone + '\'' +
        ", mobileNumber='" + mobileNumber + '\'' +
        ", workNumber='" + workNumber + '\'' +
        ", email='" + email + '\'' +
        ", email2='" + email2 + '\'' +
        ", email3='" + email3 + '\'' +
        '}';
  }

  public ContactData withFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public ContactData withMiddlename(String middlename) {
    this.middlename = middlename;
    return this;
  }

  public ContactData withLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public ContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public ContactData withNickname(String nickname) {
    this.nickname = nickname;
    return this;
  }

  public ContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public ContactData withTitle(String title) {
    this.title = title;
    return this;
  }

  public ContactData withPhoneNumber(String phone) {
    this.phone = phone;
    return this;
  }

  public ContactData withGroup(String group) {
    this.group = group;
    return this;
  }

  public ContactData withMobileNumber(String mobileNumber) {
    this.mobileNumber = mobileNumber;
    return this;
  }

  public ContactData withWorkNumber(String workNumber) {
    this.workNumber = workNumber;
    return this;
  }

  public ContactData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public ContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public ContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public ContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public ContactData withHomepage(String homepage) {
    this.homepage = homepage;
    return this;
  }

  public ContactData withBirthsday(Date birthsday) {
    this.birthsday = birthsday;
    return this;
  }

  public ContactData withAnniversary(Date anniversary) {
    this.anniversary = anniversary;
    return this;
  }

  public ContactData withSecAddress(String secAddress) {
    this.secAddress = secAddress;
    return this;
  }

  public ContactData withSecHome(String secHome) {
    this.secHome = secHome;
    return this;
  }

  public ContactData withNotes(String notes) {
    this.notes = notes;
    return this;
  }

  public ContactData withId(int id) {
    this.id = id;
    return this;
  }

  public ContactData withPhoto(File photo) {
    this.photo = photo.getPath();
    return this;
  }

  public int getId() {
    return id;
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

  public String getPhoneNumber() {
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

  public File getPhoto() {
    return new File(photo);
  }

}
