package com.mvc.dao;

import com.mvc.model.CarModel;
import com.mvc.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository("carDAO")
public class CarDAO implements ICarDAO {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    EntityManager entityManager;

    /**
     * metoda pobiera liste samochodow dla podanego uzytkownika
     */
    public List<CarModel> getUserCars(UserModel user) {
        TypedQuery<CarModel> query = entityManager.createQuery("select c from CarModel c where c.user = :user", CarModel.class);
        query.setParameter("user", user);

        return query.getResultList();
    }

    /**
     * metoda zapisuje liste samochodow do bazy
     */
    public void saveCars(List<CarModel> cars){
        if(cars != null){
            try {
                for (CarModel car : cars) {
                    entityManager.merge(car);
                }
            } catch (PersistenceException pE){
                log.error("Nie udalo sie zapisac obiektu do bazy");
            }
        }
    }

    /**
     * metoda usuwa samochody z listy samochodow
     */
    public void deleteCars(List<CarModel> cars){
        try{
            for(CarModel car : cars){
                entityManager.remove(car);
            }
        } catch (PersistenceException pE){
            log.error("Nie udalo sie usunac samochod z bazy");
        }
    }
}
