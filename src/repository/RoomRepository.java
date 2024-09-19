package repository;

import model.Reservation;
import model.Room;


import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface RoomRepository {

    Optional<Integer> addRoom(Room room);
//    Optional<Integer> getIDRoomToReserve(LocalDate checkIn, LocalDate checkOut, int roomTypeId);
Map<Integer, List<Reservation>> getIDRoomToReserve(LocalDate startDate, LocalDate endDate, int roomTypeId);
    Optional<Integer> getIDRoomToReserve(LocalDate checkIn, LocalDate checkOut, int roomTypeId, int idReservation);

}
