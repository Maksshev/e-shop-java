package servlets;

import dto.User;
import services.CartService;
import services.CookieService;
import services.ListService;
import services.RegService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class RegServlet extends HttpServlet {

    private Connection connection;
    private RegService regService;
    private ListService listService;

    public RegServlet(Connection connection) {
        this.connection = connection;
        this.regService = new RegService(connection);
        this.listService = new ListService(connection);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieService cs = new CookieService(resp, req, connection);
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = regService.createUser(login, password, firstName, lastName);
        regService.registerUser(user);
        cs.setCookie(user.getId());
        listService.writeTemplateToFile(user.getId());
        CartService cartService = new CartService(connection, user.getId());
        cartService.writeTemplateToFile();
        resp.sendRedirect("/list");
    }
}
