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
import services.ListService;
import servlets.AuthServlet;
import servlets.RegServlet;

import java.sql.Connection;

public class App {

    public static void main(String[] args) throws Exception {

        Connection connection = new DbConnection().connection();


        ResourceHandlerGenerator rhg = new ResourceHandlerGenerator();

        ContextHandler authResHandler = rhg.generateResourceHandler("src/main/resources/templates/auth", "auth.html", "/");
        ContextHandler regResHandler = rhg.generateResourceHandler("src/main/resources/templates/reg", "reg.html", "/signup");
        ContextHandler listResHandler = rhg.generateResourceHandler("src/main/resources/templates/listHTML", "list.html", "/list");


        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addServlet(new ServletHolder(new AuthServlet(connection)), "/auth");
        servletHandler.addServlet(new ServletHolder(new RegServlet(connection)), "/reg");


        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.setHandlers(new Handler[] {authResHandler, regResHandler, listResHandler, servletHandler});

        Server server = new Server(80);

        server.setHandler(handlerCollection);
        server.join();
        server.start();



    }
}
