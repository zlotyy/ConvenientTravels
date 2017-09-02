package com.mvc.service;

import com.mvc.enums.Male;
import com.mvc.model.UserModel;

import java.util.Calendar;
import java.util.List;

public interface IUserService {
    UserModel getUser(String login);
    UserModel getUser(long userId);
    /**
     * Metoda sprawdza czy login jest unikalny, a jesli nie jest to kieruje do sprawdzenia czy konto jest usuniete
     */
    boolean isLoginUnique(String login);
    boolean isAccountDeleted(String login);
    //List<UserModel> getUsersForGrid(String sortOrder, String search);     // funkcjonalnsc by byla, gdyby bylo konto admina
    boolean deleteUser(long userId);
    UserModel editUser(long userId);
    UserModel createUser(String login, String password, String mail, String phone, String name, String lastname, Male male,
                         int birthYear, String searchData, List<Integer> userRates, List<String> personalityAssessment,
                         List<Integer> drivingSkills, Calendar modifyTime, Calendar lastLoginTime, boolean isDeleted);
    void updateLastLoginTime(long userId);
}
