package repository;

import model.Room;


import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.util.Optional;

public interface RoomRepository {

    Optional<Integer> addRoom(Room room);
    Optional<Integer> getIDRoomToReserve(LocalDate checkIn, LocalDate checkOut, int roomTypeId);

}
