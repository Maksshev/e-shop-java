package servlets;

import dto.Commodity;
import services.CartService;
import services.CookieService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class CartServlet extends HttpServlet {

    private Connection connection;
    private int userId;

    public CartServlet(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieService cs = new CookieService(resp, req, connection);

        if (cs.getCookie() != null) {
            userId = Integer.parseInt(cs.getCookie().getValue());
            CartService cartService = new CartService(connection, userId);
            cartService.writeTemplateToFile();
            resp.sendRedirect("/cart");
        } else {
            resp.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idOfCommodityToAddStr = req.getParameter("add_commodity");
        String idOfCommodityToRemoveStr = req.getParameter("remove_commodity");
        CartService cartService = new CartService(connection, userId);

        if (idOfCommodityToAddStr != null) {
            int idOfCommodityToAdd = Integer.parseInt(idOfCommodityToAddStr);
            cartService.addToCart(new Commodity(idOfCommodityToAdd));
        } else {
            int idOfCommodityToRemove = Integer.parseInt(idOfCommodityToRemoveStr);
            cartService.removeFromCart(idOfCommodityToRemove);
        }

        cartService.writeTemplateToFile();
        resp.sendRedirect("/cart");
    }
}
