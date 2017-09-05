package com.mvc.dao;

import com.mvc.enums.Male;
import com.mvc.model.UserModel;
import org.springframework.stereotype.Repository;

import java.util.Calendar;
import java.util.List;

@Repository("userDAO")
public class UserDAO implements IUserDAO {

    public UserModel createUser(String login, String password, String mail, String phone, String name, String lastname, Male male,
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

        return null;
    }
}
