package services;

import dto.Commodity;
import dto.User;

import java.sql.Connection;
import java.util.List;

public class CartService {

    private Connection connection;
    private User currentUser;

    public CartService(Connection connection, User currentUser) {
        this.connection = connection;
        this.currentUser = currentUser;
    }

    public List<Commodity> getCurrentUserCartItems() {
        return null;
    }
}
