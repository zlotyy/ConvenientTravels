package com.mvc.service;

import com.mvc.dao.IDriveDAO;
import com.mvc.enums.LuggageSize;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.DriveDetailsModel;
import com.mvc.model.DriveModel;
import com.mvc.model.StopOverPlaceModel;
import com.mvc.model.UserModel;
import org.hibernate.Hibernate;
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
    public ServiceResult<DriveModel> addNewDrive(String cityStart, String streetStart, String exactPlaceStart,
                                                 String cityArrival, String streetArrival, String exactPlaceArrival, Calendar startDate, Calendar returnDate,
                                                 int passengersQuantity, int cost, LuggageSize luggageSize, boolean isSmokePermitted,
                                                 boolean isRoundTrip, String driverComment, List<StopOverPlaceModel> stopOverPlaces,
                                                 UserModel insertUser) {
        ServiceResult<DriveModel> result = new ServiceResult<>();
        Calendar timeNow = Calendar.getInstance();

        try {
            log.info("Zapisuje przejazd");

            DriveModel drive = new DriveModel();
            DriveDetailsModel driveDetails = new DriveDetailsModel();

            drive.setCityStart(cityStart);
            drive.setStreetStart(streetStart);
            drive.setExactPlaceStart(exactPlaceStart);
            drive.setCityArrival(cityArrival);
            drive.setStreetArrival(streetArrival);
            drive.setExactPlaceArrival(exactPlaceArrival);
            drive.setStartDate(startDate);
            drive.setReturnDate(returnDate);
            drive.setCost(cost);
            drive.setRoundTrip(isRoundTrip);
            drive.setStopOverPlaces(stopOverPlaces);
            drive.setInsertDate(timeNow);
            drive.setModificationDate(timeNow);
            drive.setInsertUser(insertUser);

            driveDetails.setPassengersQuantity(passengersQuantity);
            driveDetails.setLuggageSize(luggageSize);
            driveDetails.setSmokePermitted(isSmokePermitted);
            driveDetails.setDriverComment(driverComment);
            driveDetails.setDrive(drive);


            if(stopOverPlaces != null) {
                for (int i = 0; i < stopOverPlaces.size(); i++) {
                    stopOverPlaces.get(i).setDrive(drive);
                }
            }

            driveDAO.addNewDrive(drive, driveDetails);
            driveDAO.saveStopOverPlaces(stopOverPlaces);
            result.setData(drive);

        } catch (Exception e) {
            log.error("Blad podczas zapisywania przejazdu do bazy");
            result.errors.add("Błąd podczas tworzenia nowego przejazdu");
        }

        return result;
    }


    /**
     * serwis pobiera wszystkie przejazdy uzytkownika
     * @return
     */
    @Transactional
    public ServiceResult<List<DriveModel>> getUserDrives(UserModel user) {
        ServiceResult<List<DriveModel>> result = new ServiceResult<>();

        try {
            log.info("Pobieram przejazdy dla uzytkownika");

            List<DriveModel> drives = driveDAO.getUserDrives(user);
            for(DriveModel drive : drives) {
                Hibernate.initialize(drive.getStopOverPlaces());
                Hibernate.initialize(drive.getBookings());
            }

            if(drives == null){
                log.info("Nie mozna znalezc przejazdow dla uzytkownika: " + user);
                result.errors.add("Brak przejazdów dla użytkownika");
            } else {
                result.setData(drives);
            }
        } catch(Exception e){
            log.error("Blad podczas pobierania przejazdow");
            result.errors.add("Błąd podczas pobierania przejazdów z bazy danych");
        }

        return result;
    }

    /**
     * Serwis pobiera szczegoly przejazdu
     */
    @Transactional
    public ServiceResult<DriveDetailsModel> getDriveDetails(DriveModel drive){
        ServiceResult<DriveDetailsModel> result = new ServiceResult<>();
        DriveDetailsModel driveDetails;

        try {
            log.info("Pobieram szczegoly przejazdu");
            driveDetails = driveDAO.getDriveDetails(drive);
            result.setData(driveDetails);
        } catch(Exception e){
            log.error("Blad podczas pobierania szczegolow przejazdu");
            result.errors.add("Błąd podczas pobierania szczegółów przejazdu z bazy danych");
        }

        return result;
    }

    /**
     * serwis ustawia przejazdowi parametr isDeleted na true
     */
    @Transactional
    public ServiceResult<DriveModel> setDriveDeleted(DriveModel drive) {
        ServiceResult<DriveModel> result = new ServiceResult<>();

        try {
            drive.setDeleted(true);
            driveDAO.editDrive(drive);
            result.setData(drive);
            result.messages.add("Przejazd został usunięty");
        } catch (Exception e){
            log.error("Blad podczas usuwania przejazdu");
            result.errors.add("Błąd podczas usuwania przejazdu");
        }

        return result;
    }

    /**
     * serwis edytuje przejazd
     */
    @Transactional
    public ServiceResult<DriveModel> editDrive(String cityStart, String streetStart, String exactPlaceStart, Calendar startDate, String cityArrival,
                                               String streetArrival, String exactPlaceArrival, List<StopOverPlaceModel> stopOverPlaces, int passengersQuantity, int cost,
                                               LuggageSize luggageSize, boolean isSmokePermitted, boolean isRoundTrip, Calendar returnDate,
                                               String driverComment, long driveId) {
        ServiceResult<DriveModel> result = new ServiceResult<>();

        try {
            DriveModel drive = this.getDrive(driveId).getData();
            DriveDetailsModel driveDetails = this.getDriveDetails(drive).getData();

            drive.setCityStart(cityStart);
            drive.setStreetStart(streetStart);
            drive.setExactPlaceStart(exactPlaceStart);
            drive.setStartDate(startDate);
            drive.setCityArrival(cityArrival);
            drive.setExactPlaceArrival(exactPlaceArrival);
            drive.setStreetArrival(streetArrival);
            drive.setStopOverPlaces(stopOverPlaces);
            drive.setCost(cost);
            drive.setRoundTrip(isRoundTrip);
            drive.setReturnDate(returnDate);

            driveDetails.setPassengersQuantity(passengersQuantity);
            driveDetails.setLuggageSize(luggageSize);
            driveDetails.setSmokePermitted(isSmokePermitted);
            driveDetails.setDriverComment(driverComment);

            log.info("Edytuje przejazd");

            driveDAO.editDrive(drive);
            result.setData(drive);
            result.messages.add("Przejazd został edytowany");
        } catch (Exception e){
            log.error("Blad podczas edycji przejazdu");
            result.errors.add("Błąd podczas edycji przejazdu");
        }

        return result;
    }

    /**
     * serwis wyszukuje przejazd po id
     */
    public ServiceResult<DriveModel> getDrive(long driveId) {

        ServiceResult<DriveModel> result = new ServiceResult<>();
        DriveModel drive;
        try {
            drive = driveDAO.findById(driveId);
            result.setData(drive);
        } catch (Exception e){
            log.error("Blad podczas wyszukiwania przejazdu");
            result.errors.add("Błąd podczas pobierania danych przejazdu");
        }

        return result;
    }


    /**
     * serwis wyszukuje przejazdy wedlug wybranych filtrow
     */
    @Transactional
    public ServiceResult<List<DriveModel>> getDrives(String startPlace, String arrivalPlace, Calendar startDate, Calendar returnDate, boolean isRoundTrip, int maxCost, LuggageSize luggageSize, UserModel searchingUser) {
        ServiceResult<List<DriveModel>> result = new ServiceResult<>();
        Calendar timeNow = Calendar.getInstance();

        try {
            log.info("Pobieram przejazdy wedlug filtrow");

            List<DriveModel> drives = driveDAO.findDrives(startPlace, arrivalPlace, startDate, returnDate, isRoundTrip, maxCost, luggageSize, timeNow, searchingUser);
            for(DriveModel drive : drives) {
                Hibernate.initialize(drive.getStopOverPlaces());
                Hibernate.initialize(drive.getBookings());
            }

            if(drives == null){
                log.info("Nie mozna znalezc przejazdow");
                result.errors.add("Brak wyników spełniających kryteria");
            } else {
                result.setData(drives);
            }
        } catch (Exception e){
            log.error("Blad podczas wyszukiwania przejazdow");
            result.errors.add("Błąd podczas wyszukiwania przejazdów w bazie");
        }

        return result;
    }
}
