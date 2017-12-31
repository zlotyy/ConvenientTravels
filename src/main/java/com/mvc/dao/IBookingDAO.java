package com.mvc.dao;

import com.mvc.model.BookingModel;
import com.mvc.model.DriveModel;
import com.mvc.model.UserModel;

import java.util.List;

public interface IBookingDAO {
    List<BookingModel> findBookings(DriveModel drive);
    void bookDrive(BookingModel booking);
    void unbookDrive(BookingModel booking);
    List<BookingModel> findUserBookings(UserModel user);
    BookingModel findBooking(UserModel passenger, DriveModel drive);
}
