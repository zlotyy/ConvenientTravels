package com.mvc.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mvc.enums.LuggageSize;
import com.mvc.enums.Male;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "DriveDetails")
public class DriveDetailsModel implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "DetailId")
    private long detailId;

    @Column(name = "TimeFlexibility")
    private int timeFlexibility;

    @Column(name = "MaxBypassTime")
    private int maxBypassTime;

    @Column(name = "LuggageSize")
    @Enumerated(EnumType.STRING)
    private LuggageSize luggageSize;

    @Column(name = "PassengersQuantity")
    @NotNull(message = "Pole nie moze byc puste")
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
    @ElementCollection(targetClass=String.class)
    @CollectionTable(name = "Drive_PassengersComments", joinColumns = @JoinColumn(name = "DriveId"))
    private List<String> passengersComments;

    @OneToOne
    @JoinColumn(name = "DriveId")
    @JsonIgnore
    private DriveModel drive;


    public long getDetailId() {
        return detailId;
    }

    public void setDetailId(long detailId) {
        this.detailId = detailId;
    }

    public int getTimeFlexibility() {
        return timeFlexibility;
    }

    public void setTimeFlexibility(int timeFlexibility) {
        this.timeFlexibility = timeFlexibility;
    }

    public int getMaxBypassTime() {
        return maxBypassTime;
    }

    public void setMaxBypassTime(int maxBypassTime) {
        this.maxBypassTime = maxBypassTime;
    }

    public LuggageSize getLuggageSize() {
        return luggageSize;
    }

    public void setLuggageSize(LuggageSize luggageSize) {
        this.luggageSize = luggageSize;
    }

    public int getPassengersQuantity() {
        return passengersQuantity;
    }

    public void setPassengersQuantity(int passengersQuantity) {
        this.passengersQuantity = passengersQuantity;
    }

    public boolean isSmokePermitted() {
        return isSmokePermitted;
    }

    public void setSmokePermitted(boolean smokePermitted) {
        isSmokePermitted = smokePermitted;
    }

    public boolean isAnimalPermitted() {
        return isAnimalPermitted;
    }

    public void setAnimalPermitted(boolean animalPermitted) {
        isAnimalPermitted = animalPermitted;
    }

    public Male getPrefferedMale() {
        return prefferedMale;
    }

    public void setPrefferedMale(Male prefferedMale) {
        this.prefferedMale = prefferedMale;
    }

    public String getDriverComment() {
        return driverComment;
    }

    public void setDriverComment(String driverComment) {
        this.driverComment = driverComment;
    }

    public List<String> getPassengersComments() {
        return passengersComments;
    }

    public void setPassengersComments(List<String> passengersComments) {
        this.passengersComments = passengersComments;
    }

    public DriveModel getDrive() {
        return drive;
    }

    public void setDrive(DriveModel drive) {
        this.drive = drive;
    }
}
