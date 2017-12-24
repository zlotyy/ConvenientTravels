package com.mvc.dao;

import com.mvc.enums.LuggageSize;
import com.mvc.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Calendar;
import java.util.List;

@Repository("driveDAO")
public class DriveDAO implements IDriveDAO {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    EntityManager entityManager;



    /**
     * metoda zapisuje przejazd do bazy danych
     */
    public void addNewDrive(DriveModel drive, DriveDetailsModel driveDetails) {
        try {
            entityManager.persist(drive);
            entityManager.persist(driveDetails);
            log.info("Zapisano przejazd w bazie: " + drive + "\ndriveDetails: " + driveDetails);
        } catch (PersistenceException pE){
            log.error("Nie udalo sie zapisac obiektu do bazy");
        }
    }


    /**
     * metoda zapisuje liste miejsc posrednich do bazy
     */
    public void saveStopOverPlaces(List<StopOverPlaceModel> stopOverPlaces){
        if(stopOverPlaces != null){
            try {
                for (StopOverPlaceModel stopOverPlace : stopOverPlaces) {
                    entityManager.merge(stopOverPlace);
                }
            } catch (PersistenceException pE){
                log.error("Nie udalo sie zapisac obiektu do bazy");
            }
        }
    }

    /**
     * metoda pobiera liste przejazdow dla podanego uzytkownika
     */
    public List<DriveModel> getUserDrives(UserModel user) {
        TypedQuery<DriveModel> query = entityManager.createQuery("select d from DriveModel d where d.insertUser = :user", DriveModel.class);
        query.setParameter("user", user);

        return query.getResultList();
    }


    /**
     * metoda wyszukuje szczegoly przejazdu po id przejazdu
     */
    public DriveDetailsModel getDriveDetails(DriveModel drive) {
        TypedQuery<DriveDetailsModel> query = entityManager.createQuery("select dd from DriveDetailsModel dd where dd.drive = :drive", DriveDetailsModel.class);
        query.setParameter("drive", drive);

        DriveDetailsModel driveDetails = null;
        try {
            driveDetails = query.getSingleResult();
            log.info("Znaleziono szczegoly przejazdu w bazie: " + driveDetails);
        } catch (NoResultException nRE){
            log.info("DriveDAO.getDriveDetails() - nie znaleziono szczegolow przejazdu w bazie");
        }

        return driveDetails;
    }


    /**
     * metoda edytuje przejazd po id
     */
    public void editDrive(DriveModel drive){
        // wyszukaj przejazd po id
        DriveModel driveFromDB = entityManager.find(DriveModel.class, drive.getDriveId());
        log.info("driveFromDB: " + driveFromDB);

        try {
            driveFromDB = entityManager.merge(drive);
            log.info("Po zmianach - driveFromDB: " + driveFromDB);
        } catch (PersistenceException pE){
            log.error("Nie udalo sie nadpisac obiektu w bazie");
        }
    }


    /**
     * metoda wyszukuje przejazd po id
     */
    public DriveModel findById(Long id) {
        TypedQuery<DriveModel> query = entityManager.createQuery("select d from DriveModel d where d.driveId = :driveId", DriveModel.class);
        query.setParameter("driveId", id);

        DriveModel drive = null;
        try {
            drive = query.getSingleResult();
            log.info("Znaleziono przejazd w bazie: " + drive);
        } catch (NoResultException nRE){
            log.info("DriveDAO.findById() - nie znaleziono przejazdu w bazie");
        }

        return drive;
    }

    /**
     * metoda wyszukuje przejazdy wedlug filtrow
     */
    public List<DriveModel> findDrives(String startPlace, String arrivalPlace, Calendar startDate, Calendar returnDate, boolean isRoundTrip, int maxCost, LuggageSize luggageSize, Calendar nowDate, UserModel searchingUser) {
        Query query = entityManager.createQuery("select d from DriveModel d join DriveDetailsModel dDM on d.driveId = dDM.drive.driveId where " +
                "d.isDeleted = false" +
                " and d.insertUser not like :insertUser" +
                " and (d.cityStart like :startPlace" +
                " or d.streetStart like :startPlace" +
                " or d.exactPlaceStart like :startPlace)" +
                " and (d.cityArrival like :arrivalPlace" +
                " or d.streetArrival like :arrivalPlace" +
                " or d.exactPlaceArrival like :arrivalPlace)" +
//                " and (d.isRoundTrip = :isRoundTrip)" +
                " and (d.cost <= :maxCost)" +
                " and (dDM.luggageSize like :luggageSize)" +
                " and (d.startDate >= :startDate)" +
//                " and (d.returnDate <= :returnDate)" +
                " and (d.startDate >= :nowDate)"
        );

        query.setParameter("insertUser", searchingUser);
        query.setParameter("startPlace", "%"+startPlace+"%");
        query.setParameter("arrivalPlace", "%"+arrivalPlace+"%");
//        query.setParameter("isRoundTrip", isRoundTrip);
        query.setParameter("maxCost", maxCost);
        query.setParameter("luggageSize", "%"+luggageSize+"%");
//        if(luggageSize == null){
//            query.setParameter("luggageSize", "%%");
//        } else {
//            query.setParameter("luggageSize", "%"+luggageSize+"%");
//        }
        query.setParameter("startDate", startDate, TemporalType.DATE);
//        query.setParameter("returnDate", returnDate, TemporalType.TIMESTAMP);
        query.setParameter("nowDate", nowDate, TemporalType.TIMESTAMP);

        List<DriveModel> drives = null;

        try {
            drives = query.getResultList();
            log.info("Znaleziono przejazdy w bazie. Ilosc: " + drives.size());
        } catch (NoResultException nRE){
            log.info("DriveDAO.getDrives() - nie znaleziono przejazdow w bazie");
        }

        return drives;
    }
}
