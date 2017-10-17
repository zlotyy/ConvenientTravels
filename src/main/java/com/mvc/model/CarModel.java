package com.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Car")
public class CarModel implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "carId")
    private long carId;

    @Column(name = "CarBrand")
    @NotEmpty(message = "Pole nie moze byc puste")
    private String carBrand;

    @Column(name = "CarModel")
    private String carModel;

    @Column(name = "Color")
    @NotEmpty(message = "Pole nie moze byc puste")
    private String color;

    // Polaczenie 1 User do N Samochodow
    @ManyToOne
    @JoinColumn(name = "UserId")
    private UserModel user;

    public long getCarId() {
        return carId;
    }

    public void setCarId(long carId) {
        this.carId = carId;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CarModel{" +
                "carId=" + carId +
                ", carBrand='" + carBrand + '\'' +
                ", carModel='" + carModel + '\'' +
                ", color='" + color + '\'' +
                ", user=" + user +
                '}';
    }
}
