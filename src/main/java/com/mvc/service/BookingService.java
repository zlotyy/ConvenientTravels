package com.mvc.service;


import com.mvc.dao.IBookingDAO;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.BookingModel;
import com.mvc.model.DriveModel;
import com.mvc.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("bookingService")
@EnableTransactionManagement
public class BookingService implements IBookingService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IBookingDAO bookingDAO;


    /**
     * Serwis pobiera rezerwacje dla wybranego przejazdu
     */
    @Transactional
    public ServiceResult<List<BookingModel>> getBookings(DriveModel drive) {
        ServiceResult<List<BookingModel>> result = new ServiceResult<>();

        try {
            log.info("Pobieram rezerwacje dla przejazdu");

            List<BookingModel> bookings = bookingDAO.findBookings(drive);

            if(bookings == null){
                log.info("Nie mozna znalezc rezerwacji dla przejazdu: " + drive);
                result.errors.add("Brak rezerwacji dla przejazdu");
            } else {
                result.setData(bookings);
            }

        } catch(Exception e){
            log.error("Blad podczas pobierania rezerwacji");
            result.errors.add("Błąd podczas pobierania rezerwacji z bazy danych");
        }

        return result;
    }

    /**
     * Serwis dodaje rezerwacje przejazdu dla danego uzytkownikas
     */
    @Transactional
    public ServiceResult<BookingModel> bookDrive(UserModel passenger, DriveModel drive) {
        ServiceResult<BookingModel> result = new ServiceResult<>();
        BookingModel booking = new BookingModel();
        booking.setConfirmed(true);
        booking.setUser(passenger);
        booking.setDrive(drive);

        try {
            log.info("Rezerwuje przejazd: " + drive + " dla uzytkownika: " + passenger);

            bookingDAO.bookDrive(booking);
            result.setData(booking);

        } catch(Exception e){
            log.error("Blad podczas pobierania rezerwacji");
            result.errors.add("Błąd podczas pobierania rezerwacji z bazy danych");
        }


        return result;
    }
}
