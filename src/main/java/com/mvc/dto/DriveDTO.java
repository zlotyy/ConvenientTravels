package com.mvc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mvc.enums.LuggageSize;
import com.mvc.enums.Male;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.List;

public class DriveDTO {

    @NotNull(message = "Pole nie moze byc puste")
    private Calendar startDate;

    private Calendar returnDate;

    @NotEmpty(message = "Pole nie moze byc puste")
    private String cityStart;

    private String streetStart;

    private String busStopStart;

    @NotEmpty(message = "Pole nie moze byc puste")
    private String cityArrival;

    private String streetArrival;

    @JsonIgnore
    private List<String> stopOverCities;

    @JsonIgnore
    private List<String> stopOverStreets;

    @NotNull(message = "Pole nie moze byc puste")
    private boolean isRoundTrip;

    @NotNull(message = "Pole nie moze byc puste")
    private int cost;

    @Enumerated(EnumType.STRING)
    private LuggageSize luggageSize;

    @NotNull(message = "Pole nie moze byc puste")
    private int passengersQuantity;

    private boolean isSmokePermitted;

    private String driverComment;



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

    public String getDriverComment() {
        return driverComment;
    }

    public void setDriverComment(String driverComment) {
        this.driverComment = driverComment;
    }

    @Override
    public String toString() {
        return "DriveDTO{" +
                "startDate=" + startDate +
                ", returnDate=" + returnDate +
                ", cityStart='" + cityStart + '\'' +
                ", streetStart='" + streetStart + '\'' +
                ", busStopStart='" + busStopStart + '\'' +
                ", cityArrival='" + cityArrival + '\'' +
                ", streetArrival='" + streetArrival + '\'' +
                ", stopOverCities=" + stopOverCities +
                ", stopOverStreets=" + stopOverStreets +
                ", isRoundTrip=" + isRoundTrip +
                ", cost=" + cost +
                ", luggageSize=" + luggageSize +
                ", passengersQuantity=" + passengersQuantity +
                ", isSmokePermitted=" + isSmokePermitted +
                ", driverComment='" + driverComment + '\'' +
                '}';
    }
}
