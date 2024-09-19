package repository;
import model.Reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface ReservationRepository {


    boolean createReservation(Reservation reservation);

    Reservation getReservationById(int id);

    Map<Integer,Reservation> getAllActiveReservations(int roomTypeID);


    List<Reservation> getAllReservationsOfUser(int userId);

    void updateReservation(Reservation reservation);

    void cancelledReservation(int id);
}
