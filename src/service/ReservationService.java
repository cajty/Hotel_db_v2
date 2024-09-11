package service;

import model.Reservation;
import repository.ReservationRepository;
import repository.ReservationRepositoryImpl;

import java.time.LocalDate;
import java.util.List;

public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService() {
        this.reservationRepository = new ReservationRepositoryImpl();
    }

    public boolean createReservation(int userId, LocalDate startDate, LocalDate endDate, int roomTypeID) {
        Integer roomId = isValidReservation(startDate, endDate,roomTypeID);
        if (roomId == null) {
            System.out.println("No valid room found or the dates overlap with an existing reservation.");
            return false;
        }

        Reservation newReservation = new Reservation(startDate, endDate, userId, roomId);
        reservationRepository.createReservation(newReservation);
        System.out.println("New reservation created with ID: " + newReservation.getIdReservation());
        return true;
    }

    public boolean updateReservation(int idReservation, LocalDate startDate, LocalDate endDate, int userId, int roomTypeID) {
        Integer roomId = isValidReservation(idReservation, startDate, endDate );
        if (roomId == null) {
            System.out.println("No valid room found or the dates overlap with an existing reservation.");
            return false;
        }

        Reservation updatedReservation = new Reservation(startDate, endDate, userId, roomId);
        updatedReservation.getIdReservation(idReservation);
        reservationRepository.updateReservation(updatedReservation);
        System.out.println("Reservation updated with ID: " + idReservation);
        return true;
    }

    public boolean deleteReservation(int reservationId) {
        reservationRepository.deleteReservation(reservationId);
        System.out.println("Reservation deleted with ID: " + reservationId);
        return true;
    }

    public List<Reservation> getReservationsOfUser(int userId) {
        return reservationRepository.getAllReservationsOfRoomType(userId);
    }

    private Integer isValidReservation(LocalDate startDate, LocalDate endDate, int roomTypeID) {
        List<Reservation> reservations = reservationRepository.getAllReservationsOfRoomType(roomTypeID);
        for (Reservation reservation : reservations) {
            if (startDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate())) {
                return null;
            }
        }
        return 1; // Assuming roomId 1 for simplicity
    }

    private Integer isValidReservation(int idReservation, LocalDate startDate, LocalDate endDate) {

        List<Reservation> reservations = reservationRepository.getAllReservationsOfRoomType();
        for (Reservation reservation : reservations) {
            if (startDate.isBefore(reservation.getEndDate()) && endDate.isAfter(reservation.getStartDate()) && !reservation.getIdReservation().equals(idReservation)) {
                return null;
            }
        }
        return 1; // Assuming roomId 1 for simplicity
    }
}