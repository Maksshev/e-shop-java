package services;

import dto.User;

import java.sql.Connection;

public class RegService {

    private Connection connection;

    public RegService(Connection connection) {
        this.connection = connection;
    }

    public User createUser(String login, String password, String firstName, String lastName) {
        return new User(firstName, lastName, login, password);
    }

    public void registerUser(User user) {

    }
}
