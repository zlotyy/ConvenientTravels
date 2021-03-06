package com.mvc.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "Booking")
public class BookingModel implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "BookingId")
    private long bookingId;

    @Column(name = "IsConfirmed")
    @NotNull(message = "Pole nie moze byc puste")
    private boolean isConfirmed;

    // Polaczenie 1 User do N Rezerwacji
    @ManyToOne
    @JoinColumn(name = "UserId")
    @JsonBackReference
    private UserModel user;

    // Polaczenie 1 Przejazd do N Rezerwacji
    @ManyToOne
    @JoinColumn(name = "DriveId")
    @JsonBackReference
    private DriveModel drive;

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public DriveModel getDrive() {
        return drive;
    }

    public void setDrive(DriveModel drive) {
        this.drive = drive;
    }
}
