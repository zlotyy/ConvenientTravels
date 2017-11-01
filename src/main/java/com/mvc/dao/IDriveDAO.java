package com.mvc.dao;

import com.mvc.model.DriveDetailsModel;
import com.mvc.model.DriveModel;
import com.mvc.model.StopOverPlaceModel;

import java.util.List;

public interface IDriveDAO {
    void addNewDrive(DriveModel drive, DriveDetailsModel driveDetails);
    void saveStopOverPlaces(List<StopOverPlaceModel> stopOverPlaces);
}
