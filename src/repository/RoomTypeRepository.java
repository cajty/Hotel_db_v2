package repository;

import model.RoomType;
import java.util.List;

public interface RoomTypeRepository {
    void addRoomType(RoomType roomType);
    List<RoomType> getAllRoomTypes();
    RoomType getRoomTypeById(int id);
}
