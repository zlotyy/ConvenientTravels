package com.mvc.dao;

import com.mvc.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import javax.persistence.*;

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
    public void createUser(UserModel user) {
        try {
            entityManager.persist(user);
            log.info("Zapisano uzytkownika w bazie: " + user);
        } catch (PersistenceException pE){
            log.error("Nie udalo sie zapisac obiektu do bazy");
        }
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
     * metoda wyszukuje uzytkownika w bazie danych
     */
    public UserModel findByEmail(String mail) {
        TypedQuery<UserModel> query = entityManager.createQuery("select u from UserModel u where u.mail = :mail", UserModel.class);
        query.setParameter("mail", mail);

        UserModel user = null;
        try {
            user = query.getSingleResult();
            log.info("Znaleziono uzytkownika w bazie: " + user);
        } catch (NoResultException nRE){
            log.info("UserDAO.findByEmail() - nie znaleziono uzytkownika w bazie");
        }

        return user;
    }

    /**
     * metoda sprawdza czy podany nieunikalny login nalezy do uzytkownika
     */
    public boolean doesLoginBelongToUser(UserModel user) {
        boolean result;

        TypedQuery<UserModel> query = entityManager.createQuery("select u from UserModel u where u.userId = :userId and u.login = :login", UserModel.class);
        query.setParameter("userId", user.getUserId());
        query.setParameter("login", user.getLogin());

        UserModel userFromDB = null;
        try {
            userFromDB = query.getSingleResult();
            log.info("Znaleziono uzytkownika w bazie: " + user);
            result = true;
        } catch (NoResultException nRE){
            log.info("UserDAO.doesLoginBelongToUser() - nie znaleziono uzytkownika w bazie");
            result = false;
        }

        return result;
    }


    /**
     * metoda sprawdza czy podany nieunikalny email nalezy do uzytkownika
     */
    public boolean doesEmailBelongToUser(UserModel user) {
        boolean result;

        TypedQuery<UserModel> query = entityManager.createQuery("select u from UserModel u where u.userId = :userId and u.mail = :mail", UserModel.class);
        query.setParameter("userId", user.getUserId());
        query.setParameter("mail", user.getMail());

        UserModel userFromDB = null;
        try {
            userFromDB = query.getSingleResult();
            log.info("Znaleziono uzytkownika w bazie: " + user);
            result = true;
        } catch (NoResultException nRE){
            log.info("UserDAO.doesEmailBelongToUser() - nie znaleziono uzytkownika w bazie");
            result = false;
        }

        return result;
    }

    /**
     * metoda wyszukuje uzytkownika po id
     */
    public UserModel findById(Long id) {
        TypedQuery<UserModel> query = entityManager.createQuery("select u from UserModel u where u.userId = :userId", UserModel.class);
        query.setParameter("userId", id);

        UserModel user = null;
        try {
            user = query.getSingleResult();
            log.info("Znaleziono uzytkownika w bazie: " + user);
        } catch (NoResultException nRE){
            log.info("UserDAO.findById() - nie znaleziono uzytkownika w bazie");
        }

        return user;
    }

    /**
     * metoda zapisuje edytowanego uzytkownika do bazy danych
     */
    public void editUser(UserModel user) {
        // wyszukaj w bazie uzytkownika po ID
        UserModel userFromDB = entityManager.find(UserModel.class, user.getUserId());
        log.info("userFromDB: " + userFromDB);

        try {
            userFromDB = entityManager.merge(user);
            log.info("Po zmianach - userFromDB: " + userFromDB);
        } catch (PersistenceException pE){
            log.error("Nie udalo sie nadpisac obiektu w bazie");
        }
    }

}
