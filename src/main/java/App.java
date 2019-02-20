import db.DbConnection;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import servlets.*;

import java.sql.Connection;

public class App {

    public static void main(String[] args) throws Exception {

        Connection connection = new DbConnection().connection();


        ResourceHandlerGenerator rhg = new ResourceHandlerGenerator();

        ContextHandler authResHandler = rhg.generateResourceHandler("src/main/resources/templates/auth", "auth.html", "/signin");
        ContextHandler regResHandler = rhg.generateResourceHandler("src/main/resources/templates/reg", "reg.html", "/signup");
        ContextHandler listResHandler = rhg.generateResourceHandler("src/main/resources/templates/listHTML", "list.html", "/list");
        ContextHandler cartResHandler = rhg.generateResourceHandler("src/main/resources/templates/cartHTML", "cart.html", "/cart");


        ServletContextHandler servletHandler = new ServletContextHandler();
        servletHandler.addServlet(new ServletHolder(new AuthServlet(connection)), "/auth");
        servletHandler.addServlet(new ServletHolder(new RegServlet(connection)), "/reg");
        servletHandler.addServlet(new ServletHolder(new ListServlet(connection)), "/tocart");
        servletHandler.addServlet(new ServletHolder(new CartServlet(connection)), "/cartgen");
        servletHandler.addServlet(new ServletHolder(new LoggedCheckServlet(connection)), "/");


        HandlerCollection handlerCollection = new HandlerCollection();
        handlerCollection.setHandlers(new Handler[] {authResHandler, regResHandler, listResHandler, cartResHandler, servletHandler});

        Server server = new Server(80);

        server.setHandler(handlerCollection);
        server.join();
        server.start();



    }
}
