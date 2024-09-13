package service;

import model.Reservation;
import repository.ReservationRepository;
import repository.ReservationRepositoryImpl;

import javax.sound.midi.MidiDeviceReceiver;
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
        Integer idRoom = roomService.getIDRoomToReserve(startDate, endDate, roomTypeID).orElse(null);
        System.out.println( "d"+idRoom);
        if (idRoom == null) {
            System.out.println("No valid room found or the dates overlap with an existing reservation.");
            return false;
        }
        Reservation reservation = new Reservation(userId, idRoom, startDate, endDate, 0.0f, false, 0.0f);


        return reservationRepository.createReservation(reservation);


    }

    public boolean updateReservation(int idReservation, LocalDate startDate, LocalDate endDate, int userId) {
        Reservation reservation = reservationRepository.getReservationById(idReservation);
        if (reservation == null) {
            System.out.println("Reservation not found.");
            return false;
        }

        Integer idRoom = roomService.getIDRoomToReserve(startDate, endDate, reservation.getRoomId(),idReservation).orElse(null);
        if (idRoom == null) {
            System.out.println("No valid room found or the dates overlap with an existing reservation.");
            return false;
        }

        reservation.setStartDate(startDate);
        reservation.setEndDate(endDate);
        reservation.setRoomId(idRoom);
        reservation.setUserId(userId);

        reservationRepository.updateReservation(reservation);
        return true;

    }

    public boolean cancelledReservation(int reservationId) {
        reservationRepository.cancelledReservation(reservationId);
        System.out.println("Reservation deleted with ID: " + reservationId);
        return true;
    }

    public List<Reservation> getReservationsOfUser(int userId) {
        return reservationRepository.getAllReservationsOfUser(userId);
    }
}