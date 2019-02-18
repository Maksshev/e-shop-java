package dao;

import dto.Cart;

import java.sql.Connection;
import java.util.List;

public class DaoCartSql implements Dao<Cart> {

    private Connection connection;

    public DaoCartSql(Connection connection) {
        this.connection = connection;
    }

    public Cart get(int userId) {
        return null;
    }

    public List<Cart> getAll() {
        return null;
    }

    public void remove(int id) {

    }

    public void add(Cart item) {

    }
}
