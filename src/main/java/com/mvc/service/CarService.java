package com.mvc.service;

import com.mvc.dao.ICarDAO;
import com.mvc.dao.IUserDAO;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.CarModel;
import com.mvc.model.UserModel;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("carService")
public class CarService implements ICarService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    ICarDAO carDAO;

    @Autowired
    IUserDAO userDAO;

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

                List<CarModel> currentCars = userDAO.findByLogin(user.getLogin()).getCars();

                // gdy dodajemy samochody do listy lub tylko edytujemy samochody
                if(cars.size() >= currentCars.size()){
                    for (int i=0; i<currentCars.size(); i++){
                        cars.get(i).setCarId(currentCars.get(i).getCarId());
                    }
                }
                // gdy usuwamy jakis samochod z listy
                else
                {
                    List<CarModel> carsToDelete = new ArrayList<>();

                    // ustaw id dla samochodow
                    for (int i=0; i<cars.size(); i++){
                        cars.get(i).setCarId(currentCars.get(i).getCarId());
                    }

                    // usun nadmiar samochodow z bazy
                    for(int i=cars.size(); i<currentCars.size(); i++){
                        carsToDelete.add(currentCars.get(i));
                    }
                    carDAO.deleteCars(carsToDelete);
                }

                user.setCars(cars);
                carDAO.saveCars(cars);
                result.setData(cars);
            }
        } catch (Exception e){
            log.error("Blad podczas zapisywania listy samochodow do bazy");
            result.errors.add("Błąd podczas zapisywania samochodów do bazy danych");
        }

        return result;
    }


    /**
     * serwis usuwa wybrane samochody z bazy
     */
    public ServiceResult<List<CarModel>> deleteUserCars(UserModel user, List<CarModel> cars) {
        return null;
    }
}
