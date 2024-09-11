package controller;

import service.ReservationService;
import ui.Menu;
import util.Helper;
import util.LoginUser;

import java.time.LocalDate;

public class ReservationController {
    private final ReservationService reservationService;

    public ReservationController() {
        this.reservationService = new ReservationService();
    }

    public void createReservation() {
        int userId = LoginUser.getUserId();
        int roomType = Menu.menuOfRoomType();
        LocalDate[] dates = Helper.inputAndValidateDates();
        boolean success = reservationService.createReservation(userId, dates[0], dates[1], roomType);
        if (success) {
            System.out.println("Reservation created successfully.");
        } else {
            System.out.println("Failed to create reservation.");
        }
    }

    public void updateReservation() {
        int userId = LoginUser.getUserId();
        int roomType = Menu.menuOfRoomType();
        int reservationId = Helper.getIdReservationFromUser();
        LocalDate[] dates = Helper.inputAndValidateDates();
        boolean success = reservationService.updateReservation(reservationId, dates[0], dates[1], userId, roomType);
        if (success) {
            System.out.println("Reservation updated successfully.");
        } else {
            System.out.println("Failed to update reservation.");
        }
    }

    public void deleteReservation() {
        int reservationId = Helper.getIdReservationFromUser();
        boolean success = reservationService.deleteReservation(reservationId);
        if (success) {
            System.out.println("Reservation deleted successfully.");
        } else {
            System.out.println("Failed to delete reservation.");
        }
    }

    public void getReservationsOfUser() {
        int userId = LoginUser.getUserId();
        reservationService.getReservationsOfUser(userId).forEach(System.out::println);
    }
}