package com.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Cars")
public class Cars implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "carId")
    private long carId;

    @Id
    @Column(name = "UserId")
    @NotEmpty
    private long userId;

    @Column(name = "CarBrand")
    @NotEmpty
    private String carBrand;

    @Column(name = "CarModel")
    private String carModel;

    @Column(name = "Color")
    @NotEmpty
    private String color;
}
