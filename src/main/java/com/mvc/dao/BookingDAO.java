package com.mvc.dao;


import com.mvc.model.BookingModel;
import com.mvc.model.DriveModel;
import com.mvc.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository("bookingDAO")
public class BookingDAO implements IBookingDAO {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @PersistenceContext
    EntityManager entityManager;




    /**
     * metoda pobiera liste rezerwacji dla danego przejazdu
     */
    public List<BookingModel> findBookings(DriveModel drive) {
        TypedQuery<BookingModel> query = entityManager.createQuery("select b from BookingModel b where b.drive = :drive and b.isConfirmed = true", BookingModel.class);
        query.setParameter("drive", drive);

        List<BookingModel> bookings = null;

        try{
            bookings = query.getResultList();
            log.info("Znaleziono rezerwacje w bazie. Ilosc: " + bookings.size());
        } catch (NoResultException nRE){
            log.info("BookingDAO.findBookings() - nie znaleziono rezerwacji w bazie");
        }

        return bookings;
    }


    /**
     * metoda tworzy w bazie rezerwacje przejazdu
     */
    public void bookDrive(BookingModel booking) {
        try{
            entityManager.persist(booking);
            log.info("Zapisano rezerwacje w bazie: " + booking);
        } catch (PersistenceException pE){
            log.error("Nie udalo sie zapisac rezerwacji do bazy");
        }
    }

    /**
     * metoda usuwa z bazy rezerwacje przejazdu
     */
    public void unbookDrive(BookingModel booking) {
        try {
            entityManager.remove(entityManager.contains(booking) ? booking : entityManager.merge(booking));
            log.info("Usunieto rezerwacje z bazy: " + booking);
        } catch (PersistenceException pE){
            log.error("Nie udalo sie usunac rezerwacji z bazy");
        }
    }


    /**
     * metoda pobiera liste rezerwacji uzytkownika
     */
    public List<BookingModel> findUserBookings(UserModel user) {
        TypedQuery<BookingModel> query = entityManager.createQuery("select b from BookingModel b where b.user = :user", BookingModel.class);
        query.setParameter("user", user);

        List<BookingModel> bookings = null;

        try{
            bookings = query.getResultList();
            log.info("Znaleziono rezerwacje w bazie. Ilosc: " + bookings.size());
        } catch (NoResultException nRE){
            log.info("BookingDAO.findUserBookings() - nie znaleziono rezerwacji uzytkownika w bazie");
        }

        return bookings;
    }


    /**
     * metoda pobiera liste rezerwacji uzytkownika
     */
    public BookingModel findBooking(UserModel passenger, DriveModel drive) {
        TypedQuery<BookingModel> query = entityManager.createQuery("select b from BookingModel b where b.user = :user and b.drive = :drive", BookingModel.class);
        query.setParameter("user", passenger);
        query.setParameter("drive", drive);

        BookingModel booking = null;
        try {
            booking = query.getSingleResult();
            log.info("Znaleziono rezerwacje w bazie: " + booking);
        } catch (NoResultException nRE){
            log.info("BookingDAO.findBooking() - nie znaleziono rezerwacji w bazie");
        }

        return booking;
    }
}
