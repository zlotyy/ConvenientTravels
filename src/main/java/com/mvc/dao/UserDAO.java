package com.mvc.dao;

import com.mvc.helpers.Result;
import com.mvc.helpers.ResultError;
import com.mvc.helpers.ResultSuccess;
import com.mvc.model.CarModel;
import com.mvc.model.UserModel;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.sun.org.apache.regexp.internal.RE;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Repository("userDAO")
public class UserDAO implements IUserDAO {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * metoda zapisuje uzytkownika do bazy danych
     */
    public Result createUser(UserModel user, List<CarModel> cars) {
        Result result;

        try {
            entityManager.persist(user);
            if(cars != null){
                for(CarModel car : cars){
                    entityManager.persist(car);
                }
            }
            result = new ResultSuccess();
            log.info("Zapisano uzytkownika w bazie: " + user + "\nsamochody: " + cars);
        } catch (PersistenceException pE){
            log.error("Nie udalo sie zapisac obiektu do bazy");
            result = new ResultError("Operacja nie powiodła się");
        }

        if(cars != null) {
            log.info("Cars dla usera: " + cars);
        }

        return result;
    }


    /**
     * metoda wyszukuje uzytkownika w bazie danych
     * @param login
     * @param password to surowy String, niezakodowany jeszcze przez BCryptPasswordEncoder
     * @return
     */
    public UserModel findByLoginAndPassword(String login, String password) {

        TypedQuery<UserModel> query = entityManager.createQuery("select u from UserModel u where u.login = :login", UserModel.class);
        query.setParameter("login", login);

        UserModel user = null;
        try {
            user = query.getSingleResult();
            // sprawdzenie czy dla wyszukanego loginu zgadza sie haslo
            bCryptPasswordEncoder.matches(password, user.getPassword());
            log.info("Znaleziono uzytkownika w bazie: " + user);
        } catch (NoResultException nRE){
            log.info("UserDAO.findByLoginAndPassword() - nie znaleziono uzytkownika w bazie");
        }

        return user;
    }


    /**
     * metoda wyszukuje uzytkownika w bazie danych
     */
    public UserModel findByLogin(String login) {
        TypedQuery<UserModel> query = entityManager.createQuery("select u from UserModel u where u.login = :login", UserModel.class);
        query.setParameter("login", login);

        UserModel user = null;
        try {
            user = query.getSingleResult();
            log.info("Znaleziono uzytkownika w bazie: " + user);
        } catch (NoResultException nRE){
            log.info("UserDAO.findByLogin() - nie znaleziono uzytkownika w bazie");
        }

        return user;
    }


    /**
     * metoda zapisuje edytowanego uzytkownika do bazy danych
     */
    public boolean editUser(UserModel user) {
        // wyszukaj w bazie uzytkownika po ID
        UserModel userFromDB = entityManager.find(UserModel.class, user.getUserId());
        log.info("userFromDB: " + userFromDB);

        try {
            userFromDB = entityManager.merge(user);
            log.info("Po zmianach - userFromDB: " + userFromDB);

            return true;
        } catch (PersistenceException pE){
            log.error("Nie udalo sie nadpisac obiektu w bazie");

            return false;
        }
    }

    /**
     * metoda pobiera liste samochodow dla podanego uzytkownika
     */
    public List<CarModel> getUserCars(UserModel user) {
        TypedQuery<CarModel> query = entityManager.createQuery("select c from CarModel c where c.user = :user", CarModel.class);
        query.setParameter("user", user);

        List<CarModel> cars = query.getResultList();
        log.info("getUserCars() " + cars);

        return cars;
    }
}
