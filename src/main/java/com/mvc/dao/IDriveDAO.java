package com.mvc.dao;

import com.mvc.model.DriveDetailsModel;
import com.mvc.model.DriveModel;
import com.mvc.model.StopOverPlaceModel;
import com.mvc.model.UserModel;

import java.util.List;

public interface IDriveDAO {
    void addNewDrive(DriveModel drive, DriveDetailsModel driveDetails);
    void saveStopOverPlaces(List<StopOverPlaceModel> stopOverPlaces);
    List<DriveModel> getUserDrives(UserModel user);
    DriveDetailsModel getDriveDetails(DriveModel drive);
    void editDrive(DriveModel drive);
    DriveModel findById(Long driveId);
}
