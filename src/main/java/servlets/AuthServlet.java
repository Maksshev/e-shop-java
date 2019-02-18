package servlets;

import dto.User;
import services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;

public class AuthServlet extends HttpServlet {

    private Connection connection;
    private AuthService authService;

    public AuthServlet(Connection connection) {
        this.connection = connection;
        this.authService = new AuthService(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Files.copy(Paths.get("templates/auth.html"), resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = authService.authUser(login, password);
        if (user != null) {
            resp.sendRedirect("/list");
        }
    }
}
