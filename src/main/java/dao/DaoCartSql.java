package dao;

import dto.Cart;
import dto.Commodity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DaoCartSql implements Dao<Cart> {

    private Connection connection;

    private DaoCommoditySql daoCommoditySql;

    private int currentUserId;


    public DaoCartSql(Connection connection, int currentUserId) {

        this.connection = connection;

        this.daoCommoditySql = new DaoCommoditySql(connection);

        this.currentUserId = currentUserId;
    }

    public Cart get(int userId) {

        try {
            String sql = "SELECT * FROM max26_cart WHERE userId = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Map<Commodity, Integer>> cartItems = new ArrayList<Map<Commodity, Integer>>();
            while (resultSet.next()) {
                final int commodityAmount = resultSet.getInt("amount");
                int commodityIndex = resultSet.getInt("commodityId");
                final Commodity commodity = daoCommoditySql.get(commodityIndex);
                cartItems.add(new HashMap<Commodity, Integer>() {{
                    put(commodity, commodityAmount);
                }});
            }

            return new Cart(userId, cartItems);
        } catch (SQLException e) {
            throw new IllegalStateException("Something went wrong");
        }
    }

    public List<Cart> getAll() {
        throw new IllegalStateException("Method is not supplied by this implementation");
    }

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

    public void add(Cart item) {
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


}
