package com.mvc.service;


import com.mvc.dao.IBookingDAO;
import com.mvc.dao.IDriveDAO;
import com.mvc.helpers.ServiceResult;
import com.mvc.model.BookingModel;
import com.mvc.model.DriveModel;
import com.mvc.model.UserModel;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("bookingService")
@EnableTransactionManagement
public class BookingService implements IBookingService {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    IBookingDAO bookingDAO;

    @Autowired
    IDriveDAO driveDAO;


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

    /**
     * Serwis usuwa rezerwacje przejazdu dla danego uzytkownikas
     */
    @Transactional
    public ServiceResult<BookingModel> unbookDrive(UserModel passenger, BookingModel booking) {
        ServiceResult<BookingModel> result = new ServiceResult<>();

        try {
            log.info("Usuwam rezerwacje: " + booking + " dla uzytkownika: " + passenger);

            bookingDAO.unbookDrive(booking);

        } catch(Exception e){
            log.error("Blad podczas usuwania rezerwacji");
            result.errors.add("Błąd podczas usuwania rezerwacji z bazy danych");
        }

        return result;
    }

    /**
     * Serwis pobiera przejazdy zarezerwowane przez uzytkownika
     */
    @Transactional
    public ServiceResult<List<DriveModel>> getUserBookedDrives(UserModel user) {
        ServiceResult<List<DriveModel>> result = new ServiceResult<>();
        List<DriveModel> drives = new ArrayList<>();
        List<Long> drivesId = new ArrayList<>();

        try {

            log.info("Pobieram rezerwacje dla uzytkownika");

            List<BookingModel> bookings = bookingDAO.findUserBookings(user);
            if(bookings == null){
                log.info("Brak rezerwowanych przejazdow przez uzytkownika");
                result.errors.add("Brak rezerwacji dla uzytkownika");
            } else {

                log.info("Pobieram przejazdy na podstawie id");

                for (BookingModel booking : bookings) {
                    DriveModel drive;

                    Long id = booking.getDrive().getDriveId();
                    drivesId.add(id);
                    drive = driveDAO.findById(id);

                    Hibernate.initialize(drive.getStopOverPlaces());
                    Hibernate.initialize(drive.getBookings());
                    drives.add(drive);
                }
                if(bookings == null){
                    log.info("Brak rezerwowanych przejazdow przez uzytkownika");
                    result.errors.add("Brak zarezerwowanych przejazdów dla użytkownika");
                } else {
                    result.setData(drives);
                }
            }

        } catch(Exception e){
            log.error("Blad podczas pobierania rezerwacji");
            result.errors.add("Błąd podczas pobierania rezerwacji z bazy danych");
        }

        return result;
    }

    /**
     * serwis wyszukuje konkretna rezerwacje
     */
    @Transactional
    public ServiceResult<BookingModel> getBooking(UserModel passenger, DriveModel drive) {

        ServiceResult<BookingModel> result = new ServiceResult<>();
        BookingModel booking;
        try {
            booking = bookingDAO.findBooking(passenger, drive);
//            Hibernate.initialize(booking.

            result.setData(booking);
        } catch (Exception e){
            log.error("Blad podczas wyszukiwania rezerwacji");
            result.errors.add("Błąd podczas pobierania rezerwacji przejazdu");
        }

        return result;
    }
}
