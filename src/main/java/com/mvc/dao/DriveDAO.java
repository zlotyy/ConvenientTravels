package com.mvc.dao;

import com.mvc.model.DriveDetailsModel;
import com.mvc.model.DriveModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

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
}
