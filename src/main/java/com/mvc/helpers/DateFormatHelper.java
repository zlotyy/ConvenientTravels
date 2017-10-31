package com.mvc.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateFormatHelper {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    String date;
    String pattern;
    Calendar calendar;

    /**
     * @param date: String
     * @param pattern: np "yyyy-MM-dd HH:mm"
     */
    public DateFormatHelper(String date, String pattern){
        this.date = date;
        this.pattern = pattern;
        calendar = Calendar.getInstance();
    }

    /**
     * metoda zamienia Stringa na Calendar w podanym formacie
     */
    public Calendar stringToCalendar_DateTimeFormat(){
        SimpleDateFormat format = new SimpleDateFormat(pattern);

        try {
            calendar.setTime(format.parse(date));
        } catch (ParseException e) {
            log.error("Blad podczas parsowania Stringa na Calendar");
            e.printStackTrace();
        }

        return calendar;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
