package com.mvc.dao;

import com.mvc.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

@Repository("userDAO")
public class UserDAO implements IUserDAO {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    EntityManager entityManager;

    public boolean createUser(UserModel user) {

        try {
            entityManager.persist(user);
        } catch (PersistenceException pE){
            log.error("Nie udalo sie zapisac obiektu do bazy");
            return false;
        }

        //UWAGA - NIE WIEM CZY DOBRZE ZE TO ROBIE
        entityManager.close();

        return true;
    }
}
