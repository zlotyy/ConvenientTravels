package com.mvc.model;

import com.mvc.enums.Male;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "User")
public class UserModel {
    @Id
    @GeneratedValue
    @Column(name = "UserId")
    private long userId;

    @Column(name = "Login")
    @NotEmpty
    private String login;

    @Column(name = "Password")
    @NotEmpty
    private String password;

    @Column(name = "Mail")
    @NotEmpty
    private String mail;

    @Column(name = "Phone")
    @NotEmpty
    private String phone;

    @Column(name = "Name")
    @NotEmpty
    private String name;

    @Column(name = "Lastname")
    @NotEmpty
    private String lastname;

    @Column(name = "Male")
    @Enumerated(EnumType.STRING)
    @NotEmpty
    private Male male;

    @Column(name = "BirthYear")
    @NotEmpty
    private int birthYear;

    @Column(name = "SearchData")
    private String searchData;

    @Column(name = "UserRates")
    @ElementCollection(targetClass=Integer.class)
    @CollectionTable(name = "User_Rates", joinColumns = @JoinColumn(name = "UserId"))
    private List<Integer> userRates;

    @Column(name = "PersonalityAssessment")
    @ElementCollection(targetClass=String.class)
    @CollectionTable(name = "User_PersonalityAssessment", joinColumns = @JoinColumn(name = "UserId"))
    private List<String> personalityAssessment;

    @Column(name = "DrivingSkills")
    @ElementCollection(targetClass=Integer.class)
    @CollectionTable(name = "User_DrivingSkills", joinColumns = @JoinColumn(name = "UserId"))
    private List<Integer> drivingSkills;

    @Column(name = "ModifyTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyTime;

    @Column(name = "LastLoginTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastLoginTime;

    @Column(name = "IsDeleted")
    @NotEmpty
    private boolean isDeleted;

    // Polaczenie 1 User do N Przejazdow
    @OneToMany(mappedBy = "user")
    private List<DriveModel> drive;

    // Polaczenie 1 User do N Samochodow
    @OneToMany(mappedBy = "user")
    private List<CarModel> car;

    // Polaczenie 1 User do N Rezerwacji
    @OneToMany(mappedBy = "user")
    private List<BookingModel> booking;

    // Polaczenie 1 User do N Sesji
    @OneToMany(mappedBy = "user")
    private List<SessionActualModel> sessionActual;

    @OneToMany(mappedBy = "sender")
    private List<MessageModel> message_sender;

    @OneToMany(mappedBy = "receiver")
    private List<MessageModel> message_receiver;



    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Male getMale() {
        return male;
    }

    public void setMale(Male male) {
        this.male = male;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getSearchData() {
        return searchData;
    }

    public void setSearchData(String searchData) {
        this.searchData = searchData;
    }

    public List<Integer> getUserRates() {
        return userRates;
    }

    public void setUserRates(List<Integer> userRates) {
        this.userRates = userRates;
    }

    public List<String> getPersonalityAssessment() {
        return personalityAssessment;
    }

    public void setPersonalityAssessment(List<String> personalityAssessment) {
        this.personalityAssessment = personalityAssessment;
    }

    public List<Integer> getDrivingSkills() {
        return drivingSkills;
    }

    public void setDrivingSkills(List<Integer> drivingSkills) {
        this.drivingSkills = drivingSkills;
    }

    public Calendar getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Calendar modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Calendar getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Calendar lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public List<DriveModel> getDrive() {
        return drive;
    }

    public void setDrive(List<DriveModel> drive) {
        this.drive = drive;
    }

    public List<CarModel> getCar() {
        return car;
    }

    public void setCar(List<CarModel> car) {
        this.car = car;
    }

    public List<BookingModel> getBooking() {
        return booking;
    }

    public void setBooking(List<BookingModel> booking) {
        this.booking = booking;
    }

    public List<SessionActualModel> getSessionActual() {
        return sessionActual;
    }

    public void setSessionActual(List<SessionActualModel> sessionActual) {
        this.sessionActual = sessionActual;
    }

    public List<MessageModel> getMessage_sender() {
        return message_sender;
    }

    public void setMessage_sender(List<MessageModel> message_sender) {
        this.message_sender = message_sender;
    }

    public List<MessageModel> getMessage_receiver() {
        return message_receiver;
    }

    public void setMessage_receiver(List<MessageModel> message_receiver) {
        this.message_receiver = message_receiver;
    }
}
