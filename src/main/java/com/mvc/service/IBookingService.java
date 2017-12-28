package com.mvc.service;

import com.mvc.helpers.ServiceResult;
import com.mvc.model.BookingModel;
import com.mvc.model.DriveModel;
import com.mvc.model.UserModel;

import java.util.List;

public interface IBookingService {
    ServiceResult<List<BookingModel>> getBookings(DriveModel drive);
    ServiceResult<BookingModel> bookDrive(UserModel passenger, DriveModel drive);
}
