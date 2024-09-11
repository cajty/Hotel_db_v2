package repository;
import model.Reservation;

import java.util.List;


public interface ReservationRepository {

    void createReservation(Reservation reservation);

    Reservation getReservationById(int id);

    List<Reservation> getAllReservationsOfRoomType(int roomTypeId);

    void updateReservation(Reservation reservation);

    void deleteReservation(int id);
}
