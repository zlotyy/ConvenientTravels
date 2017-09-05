package com.mvc.dao;

import com.mvc.enums.Male;
import com.mvc.model.UserModel;

import java.util.Calendar;
import java.util.List;

public interface IUserDAO {
    public UserModel createUser(String login, String password, String mail, String phone, String name, String lastname, Male male, Calendar birthDate, String searchData, List<Integer> userRates, List<String> personalityAssessment, List<Integer> drivingSkills, Calendar modifyTime, Calendar lastLoginTime, boolean isDeleted);
}
