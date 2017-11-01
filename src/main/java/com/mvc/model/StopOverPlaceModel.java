package com.mvc.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "StopOverPlace")
public class StopOverPlaceModel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "stopOverPlaceId")
    private long stopOverPlaceId;

    @Column(name = "StopOverCity")
    private String stopOverCity;

    @Column(name = "StopOverStreet")
    private String stopOverStreet;

    // Polaczenie 1 Przejazd do N Miejsc posrednich
    @ManyToOne
    @JoinColumn(name = "DriveId")
    private DriveModel drive;


    public long getStopOverPlaceId() {
        return stopOverPlaceId;
    }

    public void setStopOverPlaceId(long stopOverPlaceId) {
        this.stopOverPlaceId = stopOverPlaceId;
    }

    public String getStopOverCity() {
        return stopOverCity;
    }

    public void setStopOverCity(String stopOverCity) {
        this.stopOverCity = stopOverCity;
    }

    public String getStopOverStreet() {
        return stopOverStreet;
    }

    public void setStopOverStreet(String stopOverStreet) {
        this.stopOverStreet = stopOverStreet;
    }

    public DriveModel getDrive() {
        return drive;
    }

    public void setDrive(DriveModel drive) {
        this.drive = drive;
    }

    @Override
    public String toString() {
        return "StopOverPlaceModel{" +
                "stopOverPlaceId=" + stopOverPlaceId +
                ", stopOverCity='" + stopOverCity + '\'' +
                ", stopOverStreet='" + stopOverStreet + '\'' +
                ", drive=" + drive +
                '}';
    }
}
