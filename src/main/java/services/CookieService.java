package services;

import dao.DaoUserSql;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;

public class CookieService {

    private HttpServletResponse resp;
    private HttpServletRequest req;
    private Connection connection;
    private DaoUserSql daoUserSql;
    private final String COOKIE_NAME = "e-shop";

    public CookieService(HttpServletResponse resp, HttpServletRequest req, Connection connection) {
        this.resp = resp;
        this.req = req;
        this.connection = connection;
        this.daoUserSql = new DaoUserSql(connection);
    }

    public void setCookie(int userId) {
        Cookie cookie = new Cookie(COOKIE_NAME, Integer.toString(userId));
        resp.addCookie(cookie);
    }

    public boolean checkCookie(int userId) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(COOKIE_NAME) && daoUserSql.get(Integer.parseInt(c.getValue())) != null) {
                    return true;
                }
            }
        }
        return false;
    }

    public void deleteCookie() {
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(COOKIE_NAME)) {
                    Cookie cookie = new Cookie(c.getName(), c.getValue());
                    cookie.setMaxAge(0);
                    resp.addCookie(cookie);
                }
            }
        }
    }

    public Cookie getCookie() {
        Cookie[] cookies = req.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie c : cookies) {
                if (c.getName().equals(COOKIE_NAME)) {
                    return c;
                }
            }
        }
        return null;
    }
}
