import dao.DaoCartSql;
import dao.DaoCommoditySql;
import dao.DaoUserSql;
import db.DbConnection;
import dto.Commodity;
import dto.User;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.AuthServlet;

import java.sql.Connection;

public class App {

    public static void main(String[] args) throws Exception {

        Connection connection = new DbConnection().connection();


        ResourceHandler authRes = new ResourceHandler();
        authRes.setResourceBase("templates/auth");
        authRes.setDirectoriesListed(true);
        authRes.setWelcomeFiles(new String[] {"auth.html"});
        ContextHandler authResHandler = new ContextHandler("/");
        authResHandler.setHandler(authRes);


        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addServlet(new ServletHolder(new AuthServlet(connection)), "/auth");


        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.setHandlers(new Handler[] {authResHandler, servletHandler});

        Server server = new Server(80);

        server.setHandler(handlerCollection);
        server.join();
        server.start();

//        DaoCommoditySql daoCommoditySql = new DaoCommoditySql(connection);
//
//        DaoUserSql daoUserSql = new DaoUserSql(connection);
//
//        DaoCartSql daoCartSql = new DaoCartSql(connection, -823827821);
//
//        User user = new User("Valera", "123456", "Valera", "123456");
//
//        daoCartSql.addCommodityToCart(new Commodity(5));
//
//        daoCartSql.remove(1);
//
//        daoCartSql.remove(5);
//
//        daoCartSql.addCommodityToCart(new Commodity(21));
//
//        System.out.println(daoCartSql.get(-823827821));

    }
}
