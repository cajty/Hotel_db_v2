package service;

import model.Room;
import repository.RoomRepository;
import repository.RoomRepositoryImpl;

import java.util.Optional;

public class RoomService {
    private  final  RoomRepository roomRepository;

    public RoomService() {
        this.roomRepository = new RoomRepositoryImpl();
    }

    public Optional<Integer> addRoom(Room room) {
        return roomRepository.addRoom(room);
    }
}