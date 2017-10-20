package com.mvc.service;

import com.mvc.enums.Male;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.CarModel;
import com.mvc.model.UserModel;

import java.util.Calendar;
import java.util.List;

public interface IUserService {
    ServiceResult<UserModel> getUser(String login);
    ServiceResult<UserModel> getUser(String login, String password);
    ServiceResult<UserModel> getUser(long userId);
    /**
     * Metoda sprawdza czy login jest unikalny, a jesli nie jest to kieruje do sprawdzenia czy konto jest usuniete
     */
    boolean isLoginUnique(String login);
    boolean isAccountDeleted(String login);
    List<UserModel> getUsersForGrid(String sortOrder, String search);     // funkcjonalnsc by byla, gdyby bylo konto admina
    ServiceResult<UserModel> setUserDeleted(UserModel user);
    ServiceResult<UserModel> editUser(UserModel user);
    ServiceResult<UserModel> editPassword(UserModel user, String newPassword);
    ServiceResult<UserModel> createUser(String login, String password, String mail, String phone, String name, String lastname, Male male,
                             Calendar birthDate, List<CarModel> cars);
    boolean updateLastLoginTime(UserModel user);
}