package com.mvc.service;

import com.mvc.helpers.ServiceResult;
import com.mvc.model.CarModel;
import com.mvc.model.UserModel;

import java.util.List;

public interface ICarService {
    ServiceResult<List<CarModel>> getUserCars(UserModel user);
    ServiceResult<List<CarModel>> saveUserCars(UserModel user, List<CarModel> cars);
    ServiceResult<List<CarModel>> deleteUserCars(UserModel user, List<CarModel> cars);
}
