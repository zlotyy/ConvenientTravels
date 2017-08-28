package com.mvc.model;


import com.mvc.enums.LuggageSize;
import com.mvc.enums.Male;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DriveDetails {

    @Id
    @Column(name = "DriveId")
    private long driveId;

    @Column(name = "TimeFlexibility")
    private int timeFlexibility;

    @Column(name = "MaxBypassTime")
    private int maxBypassTime;

    @Column(name = "LuggageSize")
    private LuggageSize luggageSize;

    @Column(name = "PassengersQuantity")
    @NotEmpty
    private int passengersQuantity;

    @Column(name = "IsSmokePermitted")
    private boolean isSmokePermitted;

    @Column(name = "IsAnimalPermitted")
    private boolean isAnimalPermitted;

    @Column(name = "PrefferedMale")
    private Male prefferedMale;

    @Column(name = "DriverComment")
    private String driverComment;

    @Column(name = "PassengersComments")
    private String[] passengersComments;

}
