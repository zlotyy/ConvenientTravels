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
    ServiceResult<UserModel> isLoginUnique(String login);
    ServiceResult<UserModel> isEmailUnique(String mail);
    ServiceResult<UserModel> doesLoginBelongToUser(UserModel user);
    ServiceResult<UserModel> doesEmailBelongToUser(UserModel user);
    boolean isAccountDeleted(String login);
    List<UserModel> getUsersForGrid(String sortOrder, String search);     // funkcjonalnsc by byla, gdyby bylo konto admina
    ServiceResult<UserModel> setUserDeleted(UserModel user);
    ServiceResult<UserModel> editUser(UserModel user);
    ServiceResult<UserModel> editPassword(UserModel user, String newPassword);
    ServiceResult<UserModel> createUser(String login, String password, String mail, String phone, String name, String lastname, Male male,
                             Calendar birthDate, List<CarModel> cars);
    boolean updateLastLoginTime(UserModel user);
}