package controller;

import service.ReservationService;
import ui.Menu;
import util.Helper;
import util.LoginUser;

import java.time.LocalDate;
import java.util.Optional;

public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController() {
        this.reservationService = new ReservationService();
    }

    public void createReservation() {
        int userId = LoginUser.getUserId();
        Optional<Integer> roomTypeOpt = Menu.menuOfRoomType();

        if (roomTypeOpt.isEmpty()) {
            System.out.println("Returning to main menu...");
            return;
        }

        int roomTypeId = roomTypeOpt.get();
        LocalDate[] dates = Helper.inputAndValidateDates();

        boolean success = reservationService.createReservation(userId, dates[0], dates[1], roomTypeId);
        System.out.println(success ? "Reservation created successfully." : "Failed to create reservation.");
    }

    public void updateReservation() {
        int userId = LoginUser.getUserId();
        Optional<Integer> roomTypeOpt = Menu.menuOfRoomType();

        if (roomTypeOpt.isEmpty()) {
            System.out.println("Returning to main menu...");
            return;
        }

        int reservationId = Helper.getIdReservationFromUser();
        LocalDate[] dates = Helper.inputAndValidateDates();
        boolean success = reservationService.updateReservation(reservationId, dates[0], dates[1], userId);
        System.out.println(success ? "Reservation updated successfully." : "Failed to update reservation.");
    }

    public void deleteReservation() {
        int reservationId = Helper.getIdReservationFromUser();
        boolean success = reservationService.deleteReservation(reservationId);
        System.out.println(success ? "Reservation deleted successfully." : "Failed to delete reservation.");
    }

    public void getReservationsOfUser() {
        int userId = LoginUser.getUserId();
        reservationService.getReservationsOfUser(userId).forEach(System.out::println);
    }
}