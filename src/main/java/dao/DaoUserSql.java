package dao;

import dto.User;

import java.sql.*;
import java.util.List;

public class DaoUserSql implements Dao<User> {

    private Connection connection;

    public DaoUserSql(Connection connection) {
        this.connection = connection;
    }

    public User get(int userId) {
        try {
            String sql = "SELECT * FROM max26_users\n" +
                    "WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("login"), userId);
            }
            return null;

        } catch (SQLException e) {
            throw new IllegalStateException("Something went wrong");
        }
    }

    public List<User> getAll() {
        throw new IllegalStateException("Method is not supplied by this implementation");
    }

    public void remove(int userId) {
        throw new IllegalStateException("Method is not supplied by this implementation");
    }

    public void add(User user) {
        try {
            String sql = "INSERT INTO max26_users VALUES (?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new IllegalStateException("Something went wrong");
        }
    }

    public User getUserByLoginAndPassword(String login, String password) {
        try {
            String sql = "SELECT login, id, name, surname FROM max26_users WHERE login = ? AND password = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new User(resultSet.getString("name"), resultSet.getString("surname"), resultSet.getString("login"), resultSet.getInt("id"));
            }
            return null;
        } catch (SQLException e) {
            throw new IllegalStateException("Something went wrong");
        }
    }
}
