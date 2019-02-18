package services;

import dto.Commodity;

import java.sql.Connection;
import java.util.List;

public class ListService {

    private Connection connection;

    public ListService(Connection connection) {
        this.connection = connection;
    }

    public List<Commodity> getAllCommodities() {
        return null;
    }
}
