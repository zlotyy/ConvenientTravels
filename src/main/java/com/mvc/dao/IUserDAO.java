package com.mvc.dao;


import com.mvc.model.UserModel;


public interface IUserDAO {
    void createUser(UserModel user);
    UserModel findByLoginAndPassword(String login, String password);
    UserModel findByLogin(String login);
    UserModel findById(Long userId);
    UserModel findByEmail(String mail);
    boolean doesLoginBelongToUser(UserModel user);
    boolean doesEmailBelongToUser(UserModel user);
    void editUser(UserModel user);
}
