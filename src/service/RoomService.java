package service;

import model.Room;
import repository.RoomRepository;
import repository.RoomRepositoryImpl;

import java.time.LocalDate;
import java.util.Optional;

public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService() {
        this.roomRepository = new RoomRepositoryImpl();
    }

    public Optional<Integer> addRoom(Room room) {
        return roomRepository.addRoom(room);
    }

    public Optional<Integer> getIDRoomToReserve(LocalDate startDate, LocalDate endDate, int roomTypeId) {
        return roomRepository.getIDRoomToReserve(startDate, endDate, roomTypeId);
    }

    public Optional<Integer> getIDRoomToReserve(LocalDate startDate, LocalDate endDate, int roomTypeId , int idReservation) {
        return roomRepository.getIDRoomToReserve(startDate, endDate, roomTypeId, idReservation);
    }
}