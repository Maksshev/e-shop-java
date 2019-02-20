package servlets;

import services.CookieService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class LoggedCheckServlet extends HttpServlet {

    private Connection connection;

    public LoggedCheckServlet(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieService cookieService = new CookieService(resp, req, connection);
        if (cookieService.checkCookie()) {
            resp.sendRedirect("/list");
        } else {
            resp.sendRedirect("/signin");
        }
    }
}
