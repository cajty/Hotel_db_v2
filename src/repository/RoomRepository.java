package repository;

import model.Room;

import java.util.Optional;

public interface RoomRepository {

    Optional<Integer> addRoom(Room room);

}
