package com.mvc.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "Drive")
public class DriveModel {
    @Id
    @GeneratedValue
    @Column(name = "DriveId")
    private long driveId;

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
    @ElementCollection(targetClass=String.class)
    @CollectionTable(name = "Drive_StopOverCities")
    private List<String> stopOverCities;

    @Column(name = "StopOverStreets")
    @ElementCollection(targetClass=String.class)
    @CollectionTable(name = "Drive_StopOverStreet")
    private List<String> stopOverStreets;

    @Column(name = "IsRoundTrip")
    @NotEmpty
    private boolean isRoundTrip;

    @Column(name = "Cost")
    @NotEmpty
    private int cost;

    @Column(name = "IsDeleted")
    @NotEmpty
    private boolean isDeleted;

    // Polaczenie 1 User do N Przejazdow
    @ManyToOne
    @JoinColumn(name = "UserId")
    private UserModel user;

    // Polaczenie 1 Przejazd do N Rezerwacji
    @OneToMany(mappedBy = "drive")
    private List<BookingModel> booking;



    public long getDriveId() {
        return driveId;
    }

    public void setDriveId(long driveId) {
        this.driveId = driveId;
    }

    public long getInsertUserId() {
        return insertUserId;
    }

    public void setInsertUserId(long insertUserId) {
        this.insertUserId = insertUserId;
    }

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

    public List<String> getStopOverCities() {
        return stopOverCities;
    }

    public void setStopOverCities(List<String> stopOverCities) {
        this.stopOverCities = stopOverCities;
    }

    public List<String> getStopOverStreets() {
        return stopOverStreets;
    }

    public void setStopOverStreets(List<String> stopOverStreets) {
        this.stopOverStreets = stopOverStreets;
    }

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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public List<BookingModel> getBooking() {
        return booking;
    }

    public void setBooking(List<BookingModel> booking) {
        this.booking = booking;
    }
}
