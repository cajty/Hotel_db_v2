package service;

import model.Reservation;
import repository.ReservationRepository;
import repository.ReservationRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomService roomService;

    public ReservationService() {
        this.roomService = new RoomService();
        this.reservationRepository = new ReservationRepositoryImpl();
    }

    public boolean createReservation(int userId, LocalDate startDate, LocalDate endDate, int roomTypeID) {
        return roomService.getIDRoomToReserve(startDate, endDate, roomTypeID)
                .map(roomId -> {
                    Reservation newReservation = new Reservation(userId, roomId, startDate, endDate, 0.0f, false, 0.0f);
                    reservationRepository.createReservation(newReservation);
                    System.out.println("New reservation created with ID: " + newReservation.getId());
                    return true;
                })
                .orElseGet(() -> {
                    System.out.println("No valid room found or the dates overlap with an existing reservation.");
                    return false;
                });
    }

    public boolean updateReservation(int idReservation, LocalDate startDate, LocalDate endDate, int userId) {
        return roomService.getIDRoomToReserve(startDate, endDate, reservationRepository.getRoomTypeIdByReservationID(idReservation))
                .map(roomId -> {
                    Reservation updatedReservation = new Reservation(userId, roomId, startDate, endDate, 0.0f, false, 0.0f);
                    updatedReservation.setId(idReservation);
                    reservationRepository.updateReservation(updatedReservation);
                    System.out.println("Reservation updated with ID: " + idReservation);
                    return true;
                })
                .orElseGet(() -> {
                    System.out.println("No valid room found or the dates overlap with an existing reservation.");
                    return false;
                });
    }

    public boolean deleteReservation(int reservationId) {
        reservationRepository.deleteReservation(reservationId);
        System.out.println("Reservation deleted with ID: " + reservationId);
        return true;
    }

    public List<Reservation> getReservationsOfUser(int userId) {
        return reservationRepository.getAllReservationsOfRoomType(userId);
    }
}