package com.mvc.dao;

import com.mvc.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
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
}
