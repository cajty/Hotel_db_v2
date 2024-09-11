package repository;

import model.User;

public interface UserRepository {

    void addUser(User user);


    User loginUser(String email, String password);


    boolean isEmailRegistered(String email);
}
