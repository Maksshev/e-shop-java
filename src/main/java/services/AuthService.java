package services;

import dao.DaoUserSql;
import dto.User;

import java.sql.Connection;

public class AuthService {

    private Connection connection;
    private DaoUserSql daoUserSql;

    public AuthService(Connection connection) {
        this.connection = connection;
        this.daoUserSql = new DaoUserSql(connection);
    }

    public User authUser(String login, String password) {
        return daoUserSql.getUserByLoginAndPassword(login, password);
    }
}
