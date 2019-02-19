package services;

import dao.DaoUserSql;
import dto.User;

import java.sql.Connection;

public class RegService {

    private Connection connection;
    private DaoUserSql daoUserSql;

    public RegService(Connection connection) {
        this.connection = connection;
        this.daoUserSql = new DaoUserSql(connection);
    }

    public User createUser(String login, String password, String firstName, String lastName) {
        return new User(firstName, lastName, login, password);
    }

    public void registerUser(User user) {
        daoUserSql.add(user);
    }
}
