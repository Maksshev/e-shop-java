package dao;

import dto.Commodity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoCommoditySql implements Dao<Commodity> {

    private Connection connection;

    public DaoCommoditySql(Connection connection) {
        this.connection = connection;
    }

    public Commodity get(int commodityId) {
        try {
            String sql = "SELECT * FROM max26_commodities\n" +
                    "WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, commodityId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Commodity(resultSet.getString("name"), resultSet.getInt("price"), resultSet.getInt("id"));
            } else {
                throw new IllegalStateException("Something went wrong");
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Something went wrong");
        }

    }

    public List<Commodity> getAll() {

        try {
            String sql = "SELECT * FROM max26_commodities";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Commodity> resultingCommoditiesList = new ArrayList<Commodity>();
            while (resultSet.next()) {
                resultingCommoditiesList.add(new Commodity(resultSet.getString("name"), resultSet.getInt("price"), resultSet.getInt("id")));
            }

            if (resultingCommoditiesList.size() > 0) {
                return resultingCommoditiesList;
            } else {
                throw new IllegalStateException("Something went wrong");
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Something went wrong");
        }
    }

    public void remove(int commodityId) {
        throw new IllegalStateException("Method is not supplied by this implementation");
    }

    public void add(Commodity item) {
        throw new IllegalStateException("Method is not supplied by this implementation");
    }
}
