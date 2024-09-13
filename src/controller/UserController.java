package controller;

import service.UserService;
import util.ScannerSingleton;

import java.util.Scanner;

public class UserController {
    private static final UserService userService = new UserService();
    private static final Scanner input = ScannerSingleton.getScanner();

    public void registerUser() {
        System.out.println("Enter your name:");
        input.nextLine(); // Consume the newline left-over
        String name = input.nextLine();

        System.out.println("Enter your email:");
        String email = input.nextLine();

        System.out.println("Enter your password:");
        String password = input.nextLine();

        boolean isRegistered = userService.registerUser(name, email, password);

        if (isRegistered) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Email might already be registered.");
        }
    }

    public void loginUser() {
        System.out.println("Enter your email:");
        input.nextLine(); // Consume the newline left-over
        String email = input.nextLine();

        System.out.println("Enter your password:");
        String password = input.nextLine();

        userService.loginUser(email, password);
    }
}