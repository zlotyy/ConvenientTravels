package com.mvc.dao;

import com.mvc.helpers.Result;
import com.mvc.model.CarModel;
import com.mvc.model.UserModel;

import java.util.List;

public interface IUserDAO {
    Result createUser(UserModel user, List<CarModel> cars);
    UserModel findByLoginAndPassword(String login, String password);
    UserModel findByLogin(String login);
    boolean editUser(UserModel user);
}
