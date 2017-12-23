package com.mvc.service;

import com.mvc.enums.LuggageSize;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.DriveDetailsModel;
import com.mvc.model.DriveModel;
import com.mvc.model.StopOverPlaceModel;
import com.mvc.model.UserModel;

import java.util.Calendar;
import java.util.List;

public interface IDriveService {
    ServiceResult<DriveModel> addNewDrive(String cityStart, String streetStart, String exactPlaceStart,
                                          String cityArrival, String streetArrival, String exactPlaceArrival, Calendar startDate, Calendar returnDate,
                                          int passengersQuantity, int cost, LuggageSize luggageSize, boolean isSmokePermitted,
                                          boolean isRoundTrip, String driverComment, List<StopOverPlaceModel> stopOverPlaces,
                                          UserModel insertUser);
    ServiceResult<List<DriveModel>> getUserDrives(UserModel user);
    ServiceResult<DriveDetailsModel> getDriveDetails(DriveModel drive);
    ServiceResult<DriveModel> setDriveDeleted(DriveModel drive);
    ServiceResult<DriveModel> getDrive(long driveId);
    ServiceResult<DriveModel> editDrive(String cityStart, String streetStart, String exactPlaceStart, Calendar startDate, String cityArrival,
                                        String streetArrival, String exactPlaceArrival, List<StopOverPlaceModel> stopOverPlaces, int passengersQuantity, int cost,
                                        LuggageSize luggageSize, boolean isSmokePermitted, boolean isRoundTrip, Calendar returnDate,
                                        String driverComment, long driveId);
    ServiceResult<List<DriveModel>> getDrives(String startPlace, String arrivalPlace, Calendar startDate, Calendar returnDate,
                                              boolean isRoundTrip, int maxCost, LuggageSize luggageSize);
}
