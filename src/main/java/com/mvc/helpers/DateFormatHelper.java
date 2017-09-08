package com.mvc.helpers;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFormatHelper {
    Calendar calendar;

    DateFormatHelper(Calendar calendar){
        this.calendar = calendar;
    }

    public void setDateFormat(){
//        calendar.add(Calendar.DATE, 1);
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        NOT IMPLEMENTED YET
    }

    public void setTimeFormat(){
//        NOT IMPLEMENTED YET
    }

    public void setDateTimeFormat(){
//        NOT IMPLEMENTED YET
    }
}
