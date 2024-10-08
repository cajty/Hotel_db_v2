package service;

import model.Reservation;
import model.Room;
import repository.RoomRepository;
import repository.RoomRepositoryImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService() {
        this.roomRepository = new RoomRepositoryImpl();
    }

    public Optional<Integer> addRoom(Room room) {
        return roomRepository.addRoom(room);
    }

    public Map<Integer, List<Reservation>> getIDRoomToReserve(LocalDate startDate, LocalDate endDate, int roomTypeId) {
        return roomRepository.getIDRoomToReserve(startDate, endDate, roomTypeId);
    }

    public  Optional<Integer> getIDRoomToReserve(LocalDate startDate, LocalDate endDate, int roomTypeId , int idReservation) {
        return roomRepository.getIDRoomToReserve(startDate, endDate, roomTypeId, idReservation);
    }
}