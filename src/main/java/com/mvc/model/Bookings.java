package com.mvc.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Bookings")
public class Bookings implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "BookingId")
    private long bookingId;

    @Id
    @Column(name = "DriveId")
    @NotEmpty
    private long driveId;

    @Id
    @Column(name = "UserId")
    @NotEmpty
    private long userId;

    @Column(name = "IsConfirmed")
    @NotEmpty
    private boolean isConfirmed;
}
