package com.mvc.model;

import com.mvc.enums.Male;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.Calendar;

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
    @NotEmpty
    private Male male;

    @Column(name = "BirthYear")
    @NotEmpty
    private int birthYear;

    @Column(name = "SearchData")
    private String searchData;

    @Column(name = "UserRates")
    private int[] userRates;

    @Column(name = "PersonalityAssessment")
    private String[] personalityAssessment;

    @Column(name = "DrivingSkills")
    private int[] drivingSkills;

    @Column(name = "ModifyTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar modifyTime;

    @Column(name = "LastLoginTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar lastLoginTime;

    @Column(name = "IsDeleted")
    @NotEmpty
    private boolean isDeleted;

    //CAR ID
}
