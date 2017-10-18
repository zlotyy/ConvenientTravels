package com.mvc.dao;


import com.mvc.model.UserModel;


public interface IUserDAO {
    void createUser(UserModel user);
    UserModel findByLoginAndPassword(String login, String password);
    UserModel findByLogin(String login);
    boolean editUser(UserModel user);
}
