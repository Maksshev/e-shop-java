package dao;

import dto.Commodity;

import java.sql.Connection;
import java.util.List;

public class DaoCommoditySql implements Dao<Commodity> {

    private Connection connection;

    public DaoCommoditySql(Connection connection) {
        this.connection = connection;
    }

    public Commodity get(int commodityId) {
        return null;
    }

    public List<Commodity> getAll() {
        return null;
    }

    public void remove(int commodityId) {

    }

    public void add(Commodity item) {

    }
}
