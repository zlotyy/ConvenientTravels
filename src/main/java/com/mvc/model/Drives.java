package com.mvc.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "Drives")
public class Drives implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "DriveId")
    private long driveId;

    @Id
    @Column(name = "InsertUserId")
    @NotEmpty
    private long insertUserId;

    @Column(name = "StartDate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotEmpty
    private Calendar startDate;

    @Column(name = "ReturnDate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotEmpty
    private Calendar returnDate;

    @Column(name = "InsertDate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotEmpty
    private Calendar insertDate;

    @Column(name = "ModificationDate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotEmpty
    private Calendar modificationDate;

    @Column(name = "SearchData")
    private String searchData;

    @Column(name = "CityStart")
    @NotEmpty
    private String cityStart;

    @Column(name = "StreetStart")
    private String streetStart;

    @Column(name = "BusStopStart")
    private String busStopStart;

    @Column(name = "CityArrival")
    @NotEmpty
    private String cityArrival;

    @Column(name = "StreetArrival")
    private String streetArrival;

    @Column(name = "IsFreeWay")
    private boolean isFreeWay;

    @Column(name = "StopOverCities")
    private String[] stopOverCities;

    @Column(name = "StopOverStreets")
    private String[] stopOverStreets;

    @Column(name = "IsRoundTrip")
    @NotEmpty
    private boolean isRoundTrip;

    @Column(name = "Cost")
    @NotEmpty
    private int cost;

    @Column(name = "IsDeleted")
    @NotEmpty
    private boolean isDeleted;
}
