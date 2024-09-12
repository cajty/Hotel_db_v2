package controller;

import service.UserService;
import util.ScannerSingleton;

import java.util.Scanner;

public class UserController {
    private static UserService userService = new UserService();
    private static final Scanner input = ScannerSingleton.getScanner();

    public void registerUser() {
        System.out.println("Enter your first name:");
        input.nextLine();
        String firstName = input.nextLine();

        System.out.println("Enter your last name:");
        String lastName = input.nextLine();

        System.out.println("Enter your email:");
        String email = input.nextLine();

        System.out.println("Enter your password:");
        String password = input.nextLine();

        boolean isRegistered = userService.registerUser(firstName, lastName, email, password);

        if (isRegistered) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Email might already be registered.");
        }
    }

    public void loginUser() {
        System.out.println("Enter your email:");
        input.nextLine();
        String email = input.nextLine();


        System.out.println("Enter your password:");
        String password = input.nextLine();

        userService.loginUser(email, password);
    }
}