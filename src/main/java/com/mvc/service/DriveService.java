package com.mvc.service;

import com.mvc.dao.IDriveDAO;
import com.mvc.enums.LuggageSize;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.DriveDetailsModel;
import com.mvc.model.DriveModel;
import com.mvc.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;


@Service("driveService")
@EnableTransactionManagement
public class DriveService implements IDriveService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IDriveDAO driveDAO;

    /**
     * serwis tworzy nowy przejazd
     * @return
     */
    @Transactional
    public ServiceResult<DriveModel> addNewDrive(String cityStart, String streetStart, String busStopStart,
                                                 String cityArrival, String streetArrival, Calendar startDate, Calendar returnDate,
                                                 int passengersQuantity, int cost, LuggageSize luggageSize, boolean isSmokePermitted,
                                                 boolean isRoundTrip, String driverComment, List<String> stopOverCities, List<String> stopOverStreets,
                                                 UserModel insertUser) {
        ServiceResult<DriveModel> result = new ServiceResult<>();
        Calendar timeNow = Calendar.getInstance();

        try {
            log.info("Zapisuje przejazd");

            DriveModel drive = new DriveModel();
            DriveDetailsModel driveDetails = new DriveDetailsModel();

            drive.setCityStart(cityStart);
            drive.setStreetStart(streetStart);
            drive.setBusStopStart(busStopStart);
            drive.setCityArrival(cityArrival);
            drive.setStreetArrival(streetArrival);
            drive.setStartDate(startDate);
            drive.setReturnDate(returnDate);
            drive.setCost(cost);
            drive.setRoundTrip(isRoundTrip);
            drive.setStopOverCities(stopOverCities);
            drive.setStopOverStreets(stopOverStreets);
            drive.setInsertDate(timeNow);
            drive.setModificationDate(timeNow);
            drive.setInsertUser(insertUser);

            driveDetails.setPassengersQuantity(passengersQuantity);
            driveDetails.setLuggageSize(luggageSize);
            driveDetails.setSmokePermitted(isSmokePermitted);
            driveDetails.setDriverComment(driverComment);
            driveDetails.setDrive(drive);

            driveDAO.addNewDrive(drive, driveDetails);
            result.setData(drive);

        } catch (Exception e) {
            log.error("Blad podczas zapisywania przejazdu do bazy");
            result.errors.add("Błąd podczas tworzenia nowego przejazdu");
        }

        return result;
    }
}
