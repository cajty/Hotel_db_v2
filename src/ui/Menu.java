package ui;

import controller.ReservationController;
import controller.UserController;
import util.ScannerSingleton;
import java.util.Scanner;

public class Menu {

    private static final Scanner input = ScannerSingleton.getScanner();
    private static final ReservationController reservationController = new ReservationController();
    private static final UserController userController = new UserController();

    // Main menu for choosing operations on reservations
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

    // Room type menu
    public static Integer menuOfRoomType() {
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
                case 1: return 1;
                case 2: return 2;
                case 3: return 3;
                case 4: return null;  // Null indicates returning to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (userInput != 4);

        return null;  // Return to main menu
    }

    // Login menu
    public static void menuOfLogin() {
        int userInput;

        do {
            System.out.println("\n--Welcome to Hotel--\n" +
                    "========================================================================" +
                    "\n1- Login" +
                    "\n2- Signup" +
                    "\n3- Exit" +
                    "\n========================================================================");

            userInput = getValidInput();

            switch (userInput) {
                case 1:
                    userController.loginUser();
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
                    // Admin actions for single rooms
                    break;
                case 2:
                    // Admin actions for double rooms
                    break;
                case 3:
                    // Admin actions for family rooms
                    break;
                case 4:
                    System.out.println("Returning to main menu...");
                    break;  // Ends the loop and returns to main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (userInput != 4);  // Loop will end when userInput is 4
    }


    // Helper method to validate user input
    private static int getValidInput() {
        while (!input.hasNextInt()) {
            System.out.println("Invalid input. Please enter a number.");
            input.next();  // Clear the invalid input
        }
        return input.nextInt();
    }
}
