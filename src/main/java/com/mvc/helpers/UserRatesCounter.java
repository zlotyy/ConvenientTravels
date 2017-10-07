package com.mvc.helpers;

import com.mvc.model.UserModel;

import java.util.List;

public class UserRatesCounter {

    public UserRatesCounter(){

    }

    public int returnAverageRate(List<Integer> rates){
        int avg = 0;
        int sum = 0;

        for (int r : rates){
            sum += r;
        }

        avg = (int) Math.round((double)sum / rates.size());

        return avg;
    }
}
