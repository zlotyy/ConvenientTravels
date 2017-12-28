package com.mvc.dao;


import com.mvc.model.BookingModel;
import com.mvc.model.DriveModel;
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


    public void bookDrive(BookingModel booking) {
        try{
            entityManager.persist(booking);
            log.info("Zapisano rezerwacje w bazie: " + booking);
        } catch (PersistenceException pE){
            log.error("Nie udalo sie zapisac rezerwacji do bazy");
        }
    }
}
