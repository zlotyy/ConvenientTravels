package com.mvc.dao;

import com.mvc.model.DriveDetailsModel;
import com.mvc.model.DriveModel;

public interface IDriveDAO {
    void addNewDrive(DriveModel drive, DriveDetailsModel driveDetails);
}
