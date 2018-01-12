package com.mvc.helpers;

import com.mvc.dto.DriveDTO;
import com.mvc.model.DriveDetailsModel;
import com.mvc.model.DriveModel;
import com.mvc.service.IDriveService;

public class DriveParser {


    /**
     * metoda zamienia DriveModel na DriveDTO
     */
    public static DriveDTO parseDriveModel_TO_DriveDTO(DriveModel drive, IDriveService driveService){
        DriveDetailsModel driveDetails = driveService.getDriveDetails(drive).getData();
        DriveDTO driveDTO = new DriveDTO();
        String startDate = null;
        String returnDate = null;
        String isSmokePermitted = null;
        String isRoundTrip = null;

        // zamiana daty z Calendar na Stringa
        DateFormatHelper dateFormatHelper = new DateFormatHelper(drive.getStartDate(), "yyyy-MM-dd HH:mm");
        if(drive.getStartDate() != null){
            startDate = dateFormatHelper.calendarToString_DateTimeFormat();
        }
        dateFormatHelper = new DateFormatHelper(drive.getReturnDate(), "yyyy-MM-dd HH:mm");
        if(drive.getReturnDate() != null){
            returnDate = dateFormatHelper.calendarToString_DateTimeFormat();
        }
        if(driveDetails.isSmokePermitted()){
            isSmokePermitted = "true";
        }
        if(drive.isRoundTrip()){
            isRoundTrip = "true";
        }

        driveDTO.setCityStart(drive.getCityStart());
        driveDTO.setStreetStart(drive.getStreetStart());
        driveDTO.setExactPlaceStart(drive.getExactPlaceStart());
        driveDTO.setStartDate(startDate);
        driveDTO.setCityArrival(drive.getCityArrival());
        driveDTO.setStreetArrival(drive.getStreetArrival());
        driveDTO.setExactPlaceArrival(drive.getExactPlaceArrival());
        driveDTO.setPassengersQuantity(driveDetails.getPassengersQuantity());
        driveDTO.setCost(drive.getCost());
        driveDTO.setLuggageSize(driveDetails.getLuggageSize());
        driveDTO.setIsSmokePermitted(isSmokePermitted);
        driveDTO.setIsRoundTrip(isRoundTrip);
        driveDTO.setReturnDate(returnDate);
        driveDTO.setDriverComment(driveDetails.getDriverComment());

        return driveDTO;
    }

}
