package repository;

import model.Room;

public interface RoomRepository {

    public void addRoom(Room room);
    public void updateRoom(Room room);
    public void deleteRoom(Room room);
    public  Room getRoom(int id);
}
