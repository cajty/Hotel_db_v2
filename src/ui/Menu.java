package ui;

import controller.ReservationController;
import controller.UserController;
import util.ScannerSingleton;

import java.util.Optional;
import java.util.Scanner;

public class Menu {

    private static final Scanner input = ScannerSingleton.getScanner();
    private static final ReservationController reservationController = new ReservationController();
    private static final UserController userController = new UserController();


    public static void menuOfChoose() {
        int userInput;

        do {
            System.out.println("\n--Choose--\n" +
                    "========================================================================" +
                    "\n1- Create Reservation" +
                    "\n2- Show Reservations" +
                    "\n3- Update Reservation" +
                    "\n4- Delete Reservation" +
                    "\n5- Logout" +
                    "\n========================================================================");

            userInput = getValidInput();

            switch (userInput) {
                case 1:
                    reservationController.createReservation();
                    break;
                case 2:
                    reservationController.getReservationsOfUser();
                    break;
                case 3:
                    reservationController.updateReservation();
                    break;
                case 4:
                    reservationController.deleteReservation();
                    break;
                case 5:
                    menuOfLogin();  // Logout leads back to the login menu
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (userInput != 5);
    }


    public static Optional<Integer> menuOfRoomType() {
        int userInput;
        do {
            System.out.println("\n--Choose Room Type--\n" +
                    "========================================================================" +
                    "\n1- Single" +
                    "\n2- Double" +
                    "\n3- Family" +
                    "\n4- Return to Main Menu" +
                    "\n========================================================================");

            userInput = getValidInput();

            switch (userInput) {
                case 1: return Optional.of(1);
                case 2: return Optional.of(2);
                case 3: return Optional.of(3);
                case 4: return Optional.empty();  // Optional.empty() indicates returning to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);  // Loop will continue until a valid choice is made
    }

    // Login menu
    public static void menuOfLogin() {
        int userInput;

        do {
            System.out.println("\n-- Hotel --\n" +
                    "========================================================================" +
                    "\n1- Login" +
                    "\n2- Signup" +
                    "\n3- Exit" +
                    "\n========================================================================");

            userInput = getValidInput();

            switch (userInput) {
                case 1:
                    userController.loginUser();
                    userInput = 3;
                    break;
                case 2:
                    userController.registerUser();
                    break;
                case 3:
                    System.out.println("Exiting the system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (userInput != 3);
    }

    // Admin menu placeholder
    public static void menuOfAdmin() {
        int userInput;

        do {
            System.out.println("\n--Admin Options--\n" +
                    "========================================================================" +
                    "\n1- Manage Single Rooms" +
                    "\n2- Manage Double Rooms" +
                    "\n3- Manage Family Rooms" +
                    "\n4- Return to Main Menu" +
                    "\n========================================================================");

            userInput = getValidInput();

            switch (userInput) {
                case 1:

                    break;
                case 2:

                    break;
                case 3:

                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;  // Ends the loop and returns to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (userInput != 4);
    }


    private static int getValidInput() {

        while (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Invalid input. Please enter a number.");
            input.next();
        }
        return input.nextInt();
    }
}
