package com.mvc.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "ExactPlaceStart")
    private String exactPlaceStart;

    @Column(name = "CityArrival")
    @NotEmpty(message = "Pole nie moze byc puste")
    private String cityArrival;

    @Column(name = "StreetArrival")
    private String streetArrival;

    @Column(name = "ExactPlaceArrival")
    private String exactPlaceArrival;

    @Column(name = "IsFreeWay")
    private boolean isFreeWay;

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
    //@JsonIgnore
    @JsonBackReference
    private UserModel insertUser;

    // Polaczenie 1 Przejazd do N Rezerwacji
    @OneToMany(mappedBy = "drive")
    //@JsonIgnore
    @JsonManagedReference
    private List<BookingModel> bookings;

    // Polaczenie 1 Przejazd do N Miejsc posrednich
    @OneToMany(mappedBy = "drive")
    //@JsonIgnore
    @JsonManagedReference
    private List<StopOverPlaceModel> stopOverPlaces;


    public DriveModel(){

    }

    public DriveModel(Calendar startDate, Calendar returnDate, Calendar insertDate, Calendar modificationDate, String searchData,
                      String cityStart, String streetStart, String exactPlaceStart, String cityArrival, String streetArrival,
                      String exactPlaceArrival, boolean isFreeWay, boolean isRoundTrip, int cost, boolean isDeleted, UserModel insertUser,
                      List<BookingModel> bookings, List<StopOverPlaceModel> stopOverPlaces) {
        this.startDate = startDate;
        this.returnDate = returnDate;
        this.insertDate = insertDate;
        this.modificationDate = modificationDate;
        this.searchData = searchData;
        this.cityStart = cityStart;
        this.streetStart = streetStart;
        this.exactPlaceStart = exactPlaceStart;
        this.cityArrival = cityArrival;
        this.streetArrival = streetArrival;
        this.exactPlaceArrival = exactPlaceArrival;
        this.isFreeWay = isFreeWay;
        this.isRoundTrip = isRoundTrip;
        this.cost = cost;
        this.isDeleted = isDeleted;
        this.insertUser = insertUser;
        this.bookings = bookings;
        this.stopOverPlaces = stopOverPlaces;
    }



    public long getDriveId() {
        return driveId;
    }

    public void setDriveId(long driveId) {
        this.driveId = driveId;
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

    public String getExactPlaceStart() {
        return exactPlaceStart;
    }

    public void setExactPlaceStart(String exactPlaceStart) {
        this.exactPlaceStart = exactPlaceStart;
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

    public String getExactPlaceArrival() {
        return exactPlaceArrival;
    }

    public void setExactPlaceArrival(String exactPlaceArrival) {
        this.exactPlaceArrival = exactPlaceArrival;
    }

    public boolean isFreeWay() {
        return isFreeWay;
    }

    public void setFreeWay(boolean freeWay) {
        isFreeWay = freeWay;
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

    public UserModel getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(UserModel insertUser) {
        this.insertUser = insertUser;
    }

    public List<BookingModel> getBookings() {
        return bookings;
    }

    public void setBookings(List<BookingModel> bookings) {
        this.bookings = bookings;
    }

    public List<StopOverPlaceModel> getStopOverPlaces() {
        return stopOverPlaces;
    }

    public void setStopOverPlaces(List<StopOverPlaceModel> stopOverPlaces) {
        this.stopOverPlaces = stopOverPlaces;
    }



    @Override
    public String toString() {
        return "DriveModel{" +
                "driveId=" + driveId +
                ", startDate=" + (startDate != null ? startDate.getTime() : "" ) +
                ", returnDate=" + (returnDate != null ? returnDate.getTime() : "" ) +
                ", insertDate=" + (insertDate != null ? insertDate.getTime() : "" ) +
                ", modificationDate=" + (modificationDate != null ? modificationDate.getTime() : "" ) +
                ", searchData='" + searchData + '\'' +
                ", cityStart='" + cityStart + '\'' +
                ", streetStart='" + streetStart + '\'' +
                ", exactPlaceStart='" + exactPlaceStart + '\'' +
                ", cityArrival='" + cityArrival + '\'' +
                ", streetArrival='" + streetArrival + '\'' +
                ", exactPlaceArrival='" + exactPlaceArrival + '\'' +
                ", isFreeWay=" + isFreeWay +
                ", isRoundTrip=" + isRoundTrip +
                ", cost=" + cost +
                ", isDeleted=" + isDeleted +
                ", insertUser=" + insertUser +
//                ", bookings=" + bookings +
//                ", stopOverPlaces=" + stopOverPlaces +
                '}';
    }
}
