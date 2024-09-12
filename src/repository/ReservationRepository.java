package repository;
import model.Reservation;

import java.util.List;
import java.util.Optional;


public interface ReservationRepository {


    Optional<Integer> createReservation(Reservation reservation);

    Reservation getReservationById(int id);

    Integer getRoomTypeIdByReservationID(Integer id);

    List<Reservation> getAllReservationsOfRoomType(int roomTypeId);

    void updateReservation(Reservation reservation);

    void deleteReservation(int id);
}
