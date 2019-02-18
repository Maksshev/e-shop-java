package dao;

import dto.User;

import java.sql.Connection;
import java.util.List;

public class DaoUserSql implements Dao<User> {

    private Connection connection;

    public DaoUserSql(Connection connection) {
        this.connection = connection;
    }

    public User get(int userId) {
        return null;
    }

    public List<User> getAll() {
        return null;
    }

    public void remove(int userId) {

    }

    public void add(User item) {

    }
}
