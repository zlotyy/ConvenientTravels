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

    public boolean editUser(long userId) {
        return false;
    }

    /**
     * metoda tworzy nowego uzytkownika
     * @return
     */
    @Transactional
    public boolean createUser(String login, String password, String mail, String phone, String name, String lastname, Male male,
                                Calendar birthDate, String searchData, List<Integer> userRates, List<String> personalityAssessment,
                                List<Integer> drivingSkills, Calendar modifyTime, Calendar lastLoginTime, boolean isDeleted) {

        UserModel user = new UserModel();
        user.setLogin(login);
        user.setPassword(password);
        user.setMail(mail);
        user.setPhone(phone);
        user.setName(name);
        user.setLastname(lastname);
        user.setMale(male);
        user.setBirthDate(birthDate);
        user.setSearchData(searchData);
        user.setUserRates(userRates);
        user.setPersonalityAssessment(personalityAssessment);
        user.setDrivingSkills(drivingSkills);
        user.setModifyTime(modifyTime);
        user.setLastLoginTime(lastLoginTime);
        user.setDeleted(isDeleted);

        return userDAO.createUser(user);
    }

    public boolean updateLastLoginTime(long userId) {
        return false;
    }
}
