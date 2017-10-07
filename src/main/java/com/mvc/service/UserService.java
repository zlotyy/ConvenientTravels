package com.mvc.service;

import com.mvc.dao.UserDAO;
import com.mvc.enums.Male;
import com.mvc.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.List;

@Service("userService")
@EnableTransactionManagement
public class UserService implements IUserService {

    @Autowired
    UserDAO userDAO;

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
    public boolean createUser(String login, String password, String mail, String phone, String name, String lastname, Male male,
                                Calendar birthDate, Calendar modifyTime) {

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

        return userDAO.createUser(user);
    }

    public boolean updateLastLoginTime(long userId) {
        return false;
    }
}
