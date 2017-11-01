package com.mvc.service;

import com.mvc.enums.LuggageSize;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.DriveModel;
import com.mvc.model.StopOverPlaceModel;
import com.mvc.model.UserModel;

import java.util.Calendar;
import java.util.List;

public interface IDriveService {
    ServiceResult<DriveModel> addNewDrive(String cityStart, String streetStart, String busStopStart,
                                          String cityArrival, String streetArrival, Calendar startDate, Calendar returnDate,
                                          int passengersQuantity, int cost, LuggageSize luggageSize, boolean isSmokePermitted,
                                          boolean isRoundTrip, String driverComment, List<StopOverPlaceModel> stopOverPlaces,
                                          UserModel insertUser);


}
