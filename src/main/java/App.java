import dao.DaoCommoditySql;
import db.DbConnection;

import java.sql.Connection;

public class App {

    public static void main(String[] args) {

        Connection connection = new DbConnection().connection();

        DaoCommoditySql daoCommoditySql = new DaoCommoditySql(connection);

        System.out.println(daoCommoditySql.getAll());
    }
}
