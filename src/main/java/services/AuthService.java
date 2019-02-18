package services;

import dto.User;

import java.sql.Connection;

public class AuthService {

    private Connection connection;

    public AuthService(Connection connection) {
        this.connection = connection;
    }

    public int authUser(String login, String password) {
        return 0;
    }
}
