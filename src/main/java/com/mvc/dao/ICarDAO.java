package com.mvc.dao;

import com.mvc.model.CarModel;
import com.mvc.model.UserModel;

import java.util.List;

public interface ICarDAO {
    List<CarModel> getUserCars(UserModel user);
    void saveCars(List<CarModel> cars);
    void deleteCars(List<CarModel> cars);
}