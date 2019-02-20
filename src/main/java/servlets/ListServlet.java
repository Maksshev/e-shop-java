package servlets;

import dao.DaoCartSql;
import dto.Commodity;
import services.CartService;
import services.CookieService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

public class ListServlet extends HttpServlet {

    private Connection connection;

    public ListServlet(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieService cs = new CookieService(resp, req, connection);
        int userId = Integer.parseInt(cs.getCookie().getValue());
        CartService cartService = new CartService(connection, userId);
        String commodityIdStr = req.getParameter("commodityId");
        String op = req.getParameter("op");
        if (op.equals("add")) {
            int commodityId = Integer.parseInt(commodityIdStr);
            Commodity commodity = new Commodity(commodityId);
            cartService.addToCart(commodity);
        }

        resp.sendRedirect("/list");
    }

}
