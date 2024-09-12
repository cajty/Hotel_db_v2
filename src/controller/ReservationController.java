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

        // Call the menuOfRoomType() method to get the room type
        Optional<Integer> roomTypeOpt = Menu.menuOfRoomType();

        // Check if roomType is present
        if (roomTypeOpt.isEmpty()) {
            System.out.println("Returning to main menu...");
            return;  // Exit the method if the user chose to return to the main menu
        }

        // Proceed with roomType as it is not null (roomTypeOpt is not empty)
        int roomTypeId = roomTypeOpt.get();  // Safely get the room type from the Optional

        // Prompt the user for dates and validate them
        LocalDate[] dates = Helper.inputAndValidateDates();

        // Create the reservation
        boolean success = reservationService.createReservation(userId, dates[0], dates[1], roomTypeId);

        // Provide feedback to the user based on success or failure
        if (success) {
            System.out.println("Reservation created successfully.");
        } else {
            System.out.println("Failed to create reservation.");
        }
    }


    public void updateReservation() {
        int userId = LoginUser.getUserId();




        Optional<Integer> roomTypeOpt = Menu.menuOfRoomType();


        if (roomTypeOpt.isEmpty()) {
            System.out.println("Returning to main menu...");
            return;  // Exit the method if the user chose to return to the main menu
        }

        // Proceed with roomType as it is not null (roomTypeOpt is not empty)
        int roomTypeId = roomTypeOpt.get();

        int reservationId = Helper.getIdReservationFromUser();
        LocalDate[] dates = Helper.inputAndValidateDates();
        boolean success = reservationService.updateReservation(reservationId, dates[0], dates[1], userId, roomTypeId);
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