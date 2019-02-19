package servlets;

import dto.User;
import services.AuthService;
import services.ListService;

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
    private ListService listService;

    public AuthServlet(Connection connection) {
        this.connection = connection;
        this.authService = new AuthService(connection);
        this.listService = new ListService(connection);
    }
    

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = authService.authUser(login, password);
        if (user != null) {
            listService.writeTemplateToFile();
            resp.sendRedirect("/list");
        } else {
            resp.sendRedirect("/");
        }
    }
}
