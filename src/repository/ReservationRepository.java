package repository;
import model.Reservation;

import java.util.List;
import java.util.Optional;


public interface ReservationRepository {


    boolean createReservation(Reservation reservation);

    Reservation getReservationById(int id);

    Integer getRoomTypeIdByReservationID(Integer id);

    List<Reservation> getAllReservationsOfRoomType(int roomTypeId);
    List<Reservation> getAllReservationsOfUser(int userId);

    void updateReservation(Reservation reservation);

    void cancelledReservation(int id);
}
