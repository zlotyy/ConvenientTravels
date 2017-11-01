package com.mvc.dao;

import com.mvc.model.CarModel;
import com.mvc.model.DriveDetailsModel;
import com.mvc.model.DriveModel;
import com.mvc.model.StopOverPlaceModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
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

}
