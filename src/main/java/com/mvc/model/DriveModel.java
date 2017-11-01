package com.mvc.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "Drive")
public class DriveModel implements Serializable  {
    @Id
    @GeneratedValue
    @Column(name = "DriveId")
    private long driveId;

//    @Column(name = "InsertUserId")
//    @NotNull(message = "Pole nie moze byc puste")
//    private long insertUserId;

    @Column(name = "StartDate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Pole nie moze byc puste")
    private Calendar startDate;

    @Column(name = "ReturnDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar returnDate;

    @Column(name = "InsertDate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Pole nie moze byc puste")
    private Calendar insertDate;

    @Column(name = "ModificationDate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull(message = "Pole nie moze byc puste")
    private Calendar modificationDate;

    @Column(name = "SearchData")
    private String searchData;

    @Column(name = "CityStart")
    @NotEmpty(message = "Pole nie moze byc puste")
    private String cityStart;

    @Column(name = "StreetStart")
    private String streetStart;

    @Column(name = "BusStopStart")
    private String busStopStart;

    @Column(name = "CityArrival")
    @NotEmpty(message = "Pole nie moze byc puste")
    private String cityArrival;

    @Column(name = "StreetArrival")
    private String streetArrival;

    @Column(name = "IsFreeWay")
    private boolean isFreeWay;

//    @Column(name = "StopOverCities")
//    @ElementCollection(targetClass=String.class)
//    @CollectionTable(name = "Drive_StopOverCities", joinColumns = @JoinColumn(name = "DriveId"))
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//    @JsonIgnore
//    private List<String> stopOverCities;
//
//    @Column(name = "StopOverStreets")
//    @ElementCollection(targetClass=String.class)
//    @CollectionTable(name = "Drive_StopOverStreets", joinColumns = @JoinColumn(name = "DriveId"))
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//    @JsonIgnore
//    private List<String> stopOverStreets;

    @Column(name = "IsRoundTrip")
    @NotNull(message = "Pole nie moze byc puste")
    private boolean isRoundTrip;

    @Column(name = "Cost")
    @NotNull(message = "Pole nie moze byc puste")
    private int cost;

    @Column(name = "IsDeleted", columnDefinition = "tinyint(1) default 1")
    @NotNull(message = "Pole nie moze byc puste")
    private boolean isDeleted;

    // Polaczenie 1 User do N Przejazdow
    @ManyToOne
    @JoinColumn(name = "InsertUserId")
    @JsonIgnore
    private UserModel insertUser;

    // Polaczenie 1 Przejazd do N Rezerwacji
    @OneToMany(mappedBy = "drive")
    @JsonIgnore
    private List<BookingModel> booking;

    // Polaczenie 1 Przejazd do N Miejsc posrednich
    @OneToMany(mappedBy = "drive")
    @JsonIgnore
    private List<StopOverPlaceModel> stopOverPlaces;



    public long getDriveId() {
        return driveId;
    }

    public void setDriveId(long driveId) {
        this.driveId = driveId;
    }

//    public long getInsertUserId() {
//        return insertUserId;
//    }
//
//    public void setInsertUserId(long insertUserId) {
//        this.insertUserId = insertUserId;
//    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Calendar returnDate) {
        this.returnDate = returnDate;
    }

    public Calendar getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Calendar insertDate) {
        this.insertDate = insertDate;
    }

    public Calendar getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Calendar modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getSearchData() {
        return searchData;
    }

    public void setSearchData(String searchData) {
        this.searchData = searchData;
    }

    public String getCityStart() {
        return cityStart;
    }

    public void setCityStart(String cityStart) {
        this.cityStart = cityStart;
    }

    public String getStreetStart() {
        return streetStart;
    }

    public void setStreetStart(String streetStart) {
        this.streetStart = streetStart;
    }

    public String getBusStopStart() {
        return busStopStart;
    }

    public void setBusStopStart(String busStopStart) {
        this.busStopStart = busStopStart;
    }

    public String getCityArrival() {
        return cityArrival;
    }

    public void setCityArrival(String cityArrival) {
        this.cityArrival = cityArrival;
    }

    public String getStreetArrival() {
        return streetArrival;
    }

    public void setStreetArrival(String streetArrival) {
        this.streetArrival = streetArrival;
    }

    public boolean isFreeWay() {
        return isFreeWay;
    }

    public void setFreeWay(boolean freeWay) {
        isFreeWay = freeWay;
    }

//    public List<String> getStopOverCities() {
//        return stopOverCities;
//    }
//
//    public void setStopOverCities(List<String> stopOverCities) {
//        this.stopOverCities = stopOverCities;
//    }
//
//    public List<String> getStopOverStreets() {
//        return stopOverStreets;
//    }
//
//    public void setStopOverStreets(List<String> stopOverStreets) {
//        this.stopOverStreets = stopOverStreets;
//    }

    public boolean isRoundTrip() {
        return isRoundTrip;
    }

    public void setRoundTrip(boolean roundTrip) {
        isRoundTrip = roundTrip;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public UserModel getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(UserModel insertUser) {
        this.insertUser = insertUser;
    }

    public List<BookingModel> getBooking() {
        return booking;
    }

    public void setBooking(List<BookingModel> booking) {
        this.booking = booking;
    }

    public List<StopOverPlaceModel> getStopOverPlaces() {
        return stopOverPlaces;
    }

    public void setStopOverPlaces(List<StopOverPlaceModel> stopOverPlaces) {
        this.stopOverPlaces = stopOverPlaces;
    }
}
