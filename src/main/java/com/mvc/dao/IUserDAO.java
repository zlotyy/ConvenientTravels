package com.mvc.dao;

import com.mvc.model.UserModel;

public interface IUserDAO {
    boolean createUser(UserModel user);
}
