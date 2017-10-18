package com.mvc.service;

import com.mvc.helpers.ServiceResult;
import com.mvc.model.CarModel;
import com.mvc.model.UserModel;

import java.util.List;

public interface ICarService {
    ServiceResult<List<CarModel>> getUserCars(UserModel user);
}
