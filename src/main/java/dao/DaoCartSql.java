package dao;

import dto.CartItem;
import dto.Commodity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoCartSql implements Dao<CartItem> {

    private Connection connection;

    private DaoCommoditySql daoCommoditySql;

    private int currentUserId;


    public DaoCartSql(Connection connection, int currentUserId) {

        this.connection = connection;

        this.daoCommoditySql = new DaoCommoditySql(connection);

        this.currentUserId = currentUserId;
    }

    public CartItem get(int userId) {
        throw new IllegalStateException("Method is not supplied by this implementaion");
    }

    public List<CartItem> getAll() {

        try {
            String sql = "SELECT * FROM max26_cart WHERE userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, currentUserId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<CartItem> cartItems = new ArrayList<>();
            while (resultSet.next()) {
                final int commodityAmount = resultSet.getInt("amount");
                int commodityIndex = resultSet.getInt("commodityId");
                final Commodity commodity = daoCommoditySql.get(commodityIndex);
                String name = commodity.getName();
                int price = commodity.getPrice();
                cartItems.add(new CartItem(commodityIndex, name, price, commodityAmount));
            }

            return cartItems;
        } catch (SQLException e) {
            throw new IllegalStateException("Something went wrong");
        }    }

    public void remove(int commodityId) {

        try {
            int commodityAmount = getCommodityInCartAmount(commodityId);

            String sql = commodityAmount > 1 ? "UPDATE max26_cart SET amount = amount - 1 WHERE userId = ? AND commodityId = ?" : "DELETE FROM max26_cart WHERE userId = ? AND commodityId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, currentUserId);
            preparedStatement.setInt(2, commodityId);
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException("Something went wrong");
        }

    }

    public void add(CartItem item) {
        throw new IllegalStateException("Method is not supplied by this implementation");
    }

    public void addCommodityToCart(Commodity commodity) {
        try {
            int commodityAmount = getCommodityInCartAmount(commodity.getId());


            String sql = commodityAmount >= 1 ? "UPDATE max26_cart SET amount = amount + 1 WHERE userId = ? AND commodityId = ?" : "INSERT INTO max26_cart(userId, commodityId) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, currentUserId);
            preparedStatement.setInt(2, commodity.getId());
            preparedStatement.execute();

        } catch (SQLException e) {
            throw new IllegalStateException("Something went wrong");
        }
    }

    private int getCommodityInCartAmount(int commodityId) {
        try {
            String sql = "SELECT amount FROM max26_cart\n" +
                    "WHERE userId = ? AND commodityId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, currentUserId);
            preparedStatement.setInt(2, commodityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("amount");
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Something went wrong");
        }

    }


    public int getTotalSum() {
        try {
            String sql = "SELECT sum(price*amount) FROM max26_commodities JOIN max26_cart ON id = commodityId AND userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, currentUserId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1);
            } else {
                return 0;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Something went wrong");
        }
    }


}
