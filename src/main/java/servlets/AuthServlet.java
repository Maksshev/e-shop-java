package servlets;

import dto.User;
import services.AuthService;
import services.CartService;
import services.CookieService;
import services.ListService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;

public class AuthServlet extends HttpServlet {

    private Connection connection;
    private AuthService authService;
    private ListService listService;
    private final String IS_NOT_LOGGED_TEMPLATE = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><title>You are not logged</title></head><body><div><span>Please,</span> <a href=\"/\">sign in</a> <span>or</span> <a href=\"/signup\">sign up</a></div></body></html>";

    public AuthServlet(Connection connection) {
        this.connection = connection;
        this.authService = new AuthService(connection);
        this.listService = new ListService(connection);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieService cs = new CookieService(resp, req, connection);
        cs.deleteCookie();

        try (Writer fileWriterCart = new FileWriter(new File("src/main/resources/templates/cartHTML/cart.html"));
             Writer fileWriterList = new FileWriter(new File("src/main/resources/templates/listHTML/list.html"))

        ) {
            fileWriterCart.write(IS_NOT_LOGGED_TEMPLATE);
            fileWriterList.write(IS_NOT_LOGGED_TEMPLATE);
        } catch (IOException e) {
            e.printStackTrace();
           throw new IllegalStateException("Something went wrong");
        }

        resp.sendRedirect("/");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieService cs = new CookieService(resp, req, connection);
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        User user = authService.authUser(login, password);
        if (user != null) {
            CartService cartService = new CartService(connection, user.getId());
            cartService.writeTemplateToFile();
            listService.writeTemplateToFile(user.getId());
            cs.setCookie(user.getId());
            resp.sendRedirect("/list");
        } else {
            resp.sendRedirect("/");
        }
    }


}
