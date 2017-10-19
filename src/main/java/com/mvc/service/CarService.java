package com.mvc.service;

import com.mvc.dao.ICarDAO;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.CarModel;
import com.mvc.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("carService")
public class CarService implements ICarService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ICarDAO carDAO;

    /**
     * serwis pobiera liste samochodow uzytkownika
     */
    @Transactional
    public ServiceResult<List<CarModel>> getUserCars(UserModel user) {
        ServiceResult<List<CarModel>> result = new ServiceResult<>();

        try {
            log.info("Pobieram samochody dla uzytkownika");

            // pobierz samochody po uzytkowniku
            List<CarModel> cars = carDAO.getUserCars(user);

            if(cars == null){
                log.info("Nie mozna znalezc samochodow dla uzytkownika: " + user);
                result.errors.add("Brak samochodów dla użytkownika");
            } else {
                result.setData(cars);
            }
        } catch(Exception e){
            log.error("Blad podczas pobierania samochodow");
            result.errors.add("Błąd podczas pobierania samochodów z bazy danych");
        }

        return result;
    }


    /**
     * serwis zapisuje liste samochodow uzytkownika
     */
    @Transactional
    public ServiceResult<List<CarModel>> saveUserCars(UserModel user, List<CarModel> cars){
        ServiceResult<List<CarModel>> result = new ServiceResult<>();

        try {
            if(cars != null) {
                for (int i = 0; i < cars.size(); i++) {
                    cars.get(i).setUser(user);
                }
            }

            carDAO.saveCars(cars);
            result.setData(cars);

        } catch (Exception e){
            log.error("Blad podczas zapisywania listy samochodow do bazy");
            result.errors.add("Błąd podczas zapisywania samochodów do bazy danych");
        }

        return result;
    }
}
