package com.mvc.service;

import com.mvc.dao.ICarDAO;
import com.mvc.dao.IUserDAO;
import com.mvc.enums.Male;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.CarModel;
import com.mvc.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service("userService")
@EnableTransactionManagement
public class UserService implements IUserService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IUserDAO userDAO;

    @Autowired
    ICarDAO carDAO;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * serwis szuka uzytkownika po loginie
     * @return
     */
    public ServiceResult<UserModel> getUser(String login) {
        ServiceResult<UserModel> result = new ServiceResult<>();
        UserModel user;
        try {
            user = userDAO.findByLogin(login);
            result.setData(user);
        } catch (Exception e){
            log.error("Blad podczas wyszukiwania uzytkownika");
            result.errors.add("Błąd podczas pobierania danych użytkownika");
        }

        return result;
    }

    /**
     * serwis szuka uzytkownika po loginie i hasle
     * @return
     */
    public ServiceResult<UserModel> getUser(String login, String password) {

        ServiceResult<UserModel> result = new ServiceResult<>();
        UserModel user;
        try {
            user = userDAO.findByLoginAndPassword(login, password);
            result.setData(user);
        } catch (Exception e){
            log.error("Blad podczas wyszukiwania uzytkownika");
            result.errors.add("Błąd podczas pobierania danych użytkownika");
        }

        return result;
    }

    public ServiceResult<UserModel> getUser(long userId) {
        return null;
    }

    public boolean isLoginUnique(String login) {
        return false;
    }

    public boolean isAccountDeleted(String login) {
        return false;
    }

    public List<UserModel> getUsersForGrid(String sortOrder, String search) {
        return null;
    }

    /**
     * serwis ustawia uzytkowniki parametr isDeleted na true
     */
    @Transactional
    public ServiceResult<UserModel> setUserDeleted(UserModel user) {
        ServiceResult<UserModel> result = new ServiceResult<>();

        try {
            user.setDeleted(true);
            userDAO.editUser(user);
            result.setData(user);
        } catch (Exception e){
            log.error("Blad podczas usuwania uzytkownika");
            result.errors.add("Błąd podczas usuwania użytkownika");
        }

        return result;
    }

    /**
     * serwis aktualizuje dane uzytkownika
     * @return
     */
    @Transactional
    public ServiceResult<UserModel> editUser(UserModel user) {
        ServiceResult<UserModel> result = new ServiceResult<>();

        try {
            userDAO.editUser(user);
            result.setData(user);
        } catch (Exception e){
            log.error("Blad podczas nadpisywania uzytkownika do bazy");
            result.errors.add("Błąd podczas edycji użytkownika");
        }

        return result;
    }

    /**
     * serwis aktualizuje haslo uzytkownika
     * @return
     */
    @Transactional
    public ServiceResult<UserModel> editPassword(UserModel user, String newPassword) {
        ServiceResult<UserModel> result = new ServiceResult<>();

        try {
            user.setPassword(bCryptPasswordEncoder.encode(newPassword));
            userDAO.editUser(user);
            result.setData(user);
        } catch (Exception e){
            log.error("Blad podczas zmiany hasla");
            result.errors.add("Błąd podczas zmiany hasła");
        }

        return result;
    }

    /**
     * serwis tworzy nowego uzytkownika
     * @return
     */
    @Transactional
    public ServiceResult<UserModel> createUser(String login, String password, String mail, String phone, String name, String lastname, Male male,
                                    Calendar birthDate, List<CarModel> cars) {

        ServiceResult<UserModel> result = new ServiceResult<>();
        Calendar timeNow = Calendar.getInstance();

        try{
            // sprawdz czy login unikalny (wywolaj serwis)

            log.info("Zapisuje uzytkownika");

            UserModel user = new UserModel();
            user.setLogin(login);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user.setMail(mail);
            user.setPhone(phone);
            user.setName(name);
            user.setLastname(lastname);
            user.setMale(male);
            user.setBirthDate(birthDate);
            user.setModifyTime(timeNow);
            user.setCars(cars);

            if(cars != null) {
                for (int i = 0; i < cars.size(); i++) {
                    cars.get(i).setUser(user);
                }
            }

            userDAO.createUser(user);
            carDAO.saveCars(cars);
            result.setData(user);

        } catch (Exception e) {
            log.error("Blad podczas zapisywania uzytkownika do bazy");
            result.errors.add("Błąd podczas zapisywania użytkownika do bazy danych");
        }

        return result;
    }

    /**
     * serwis uatualnia czas ostatniego logowania
     */
    @Transactional
    public boolean updateLastLoginTime(UserModel user) {
        boolean result;
        Calendar timeNow = Calendar.getInstance();

        try {
            user.setLastLoginTime(timeNow);
            userDAO.editUser(user);
            result = true;
        } catch (Exception e) {
            log.error("Blad podczas uaktualniania czasu ostatniego logowania uzytkownika");
            result = false;
        }

        return result;
    }
}
