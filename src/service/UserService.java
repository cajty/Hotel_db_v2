package service;

import model.User;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import util.LoginUser;

public class UserService {

    private final UserRepository userRepository;

    // Constructor to initialize UserRepository
    public UserService() {
        this.userRepository = new UserRepositoryImpl();
    }

    // Method to register a new user
    public boolean registerUser(String name, String email, String password) {
        if (userRepository.isEmailRegistered(email)) {
            System.out.println("Email already registered!");
            return false; // Email already exists
        }

        // Create the User object with the plain password
        User user = new User(name, email, password);

        // Add the user to the database
        userRepository.addUser(user);
        System.out.println("User registered successfully with ID: " + user.getId());
        return true;
    }

    // Method to authenticate a user
    public void loginUser(String email, String password) {
        // Use the plain password for comparison
        User user = userRepository.loginUser(email, password);

        if (user != null) {
            LoginUser.setLoginUser(user.getId());
            System.out.println("Welcome: " + user.getName());
        } else {
            System.out.println("Invalid email or password.");
        }
    }
}