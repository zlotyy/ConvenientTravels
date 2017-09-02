package com.mvc.service;

import com.mvc.dao.UserDAO;
import com.mvc.enums.Male;
import com.mvc.model.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service("userService")
public class UserService implements IUserService {

    @Autowired
    UserDAO userDAO;

    public UserModel getUser(String login) {
        return null;
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

    public boolean deleteUser(long userId) {
        return false;
    }

    public UserModel editUser(long userId) {
        return null;
    }

    /**
     * metoda tworzy nowego uzytkownika
     * @return
     */
    public UserModel createUser(String login, String password, String mail, String phone, String name, String lastname, Male male,
                                int birthYear, String searchData, List<Integer> userRates, List<String> personalityAssessment,
                                List<Integer> drivingSkills, Calendar modifyTime, Calendar lastLoginTime, boolean isDeleted) {

        return userDAO.createUser(login, password, mail, phone, name, lastname, male, birthYear, searchData, userRates, personalityAssessment,
                drivingSkills, modifyTime, lastLoginTime, isDeleted);
    }

    public void updateLastLoginTime(long userId) {

    }
}
