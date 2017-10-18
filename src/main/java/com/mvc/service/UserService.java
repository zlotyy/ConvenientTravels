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

    /**
     * serwis szuka uzytkownika po loginie
     * @return
     */
    public UserModel getUser(String login) {

        return userDAO.findByLogin(login);
    }

    /**
     * serwis szuka uzytkownika po loginie i hasle
     * @return
     */
    public UserModel getUser(String login, String password) {

        return userDAO.findByLoginAndPassword(login, password);
    }

    public UserModel getUser(long userId) {
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
    public boolean setUserDeleted(UserModel user) {
        user.setDeleted(true);

        return userDAO.editUser(user);
    }

    /**
     * serwis aktualizuje dane uzytkownika
     * @return
     */
    @Transactional
    public boolean editUser(UserModel user) {

        return userDAO.editUser(user);
    }

    /**
     * serwis aktualizuje haslo uzytkownika
     * @return
     */
    @Transactional
    public boolean editPassword(UserModel user, String newPassword) {
        user.setPassword(newPassword);

        return userDAO.editUser(user);
    }

    /**
     * serwis tworzy nowego uzytkownika
     * @return
     */
    @Transactional
    public ServiceResult<UserModel> createUser(String login, String password, String mail, String phone, String name, String lastname, Male male,
                                    Calendar birthDate, Calendar modifyTime) {

        ServiceResult<UserModel> result = new ServiceResult<>();

        try{
            // sprawdz czy login unikalny (wywolaj serwis)

            log.info("Zapisuje uzytkownika");

            // szyfruj haslo
            // ustaw date

            UserModel user = new UserModel();
            user.setLogin(login);
            user.setPassword(password);
            user.setMail(mail);
            user.setPhone(phone);
            user.setName(name);
            user.setLastname(lastname);
            user.setMale(male);
            user.setBirthDate(birthDate);
            user.setModifyTime(modifyTime);

            userDAO.createUser(user);
            result.setData(user);

        } catch (Exception e) {
            log.error("Blad podczas zapisywania uzytkownika do bazy");
            result.errors.add("Błąd podczas zapisywania użytkownika do bazy");
        }
        //
//        if(cars != null) {
//            for (int i = 0; i < cars.size(); i++) {
//                cars.get(i).setUser(user);
//            }
//        }
//
//        return userDAO.createUser(user, cars);
        return result;
    }

    /**
     * serwis uatualnia czas ostatniego logowania
     */
    @Transactional
    public boolean updateLastLoginTime(UserModel user) {
        Calendar timeNow = Calendar.getInstance();
        user.setLastLoginTime(timeNow);

        return userDAO.editUser(user);
    }
}
