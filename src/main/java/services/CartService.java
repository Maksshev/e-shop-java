package services;

import dao.DaoCartSql;
import dao.DaoUserSql;
import dto.CartItem;
import dto.Commodity;
import dto.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class CartService {

    private Connection connection;
    private DaoCartSql daoCartSql;
    private DaoUserSql daoUserSql;
    private final Configuration cfg = new Configuration();
    private int userId;

    {
        cfg.setClassForTemplateLoading(this.getClass(), "../templates/cartFTL");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }

    public CartService(Connection connection, int userId) {
        this.connection = connection;
        this.userId = userId;
        this.daoCartSql = new DaoCartSql(connection, userId);
        this.daoUserSql = new DaoUserSql(connection);
    }

    public List<CartItem> getCurrentUserCartItems() {
        return daoCartSql.getAll();
    }

    public void writeTemplateToFile() {
        Map<String, Object> input = new HashMap<>();
        input.put("user", daoUserSql.get(userId));
        input.put("items", getCurrentUserCartItems());
        input.put("sum", daoCartSql.getTotalSum());
        try (Writer fileWriter = new FileWriter(new File("src/main/resources/templates/cartHTML/cart.html"))) {
            Template template = cfg.getTemplate("cart.ftl");
            template.process(input, fileWriter);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException("Something went wrong");
        }
    }

    public void addToCart(Commodity commodity) {
        daoCartSql.addCommodityToCart(commodity);
    }

    public void removeFromCart(int commodityId) {
        daoCartSql.remove(commodityId);
    }

    public int getTotalSum() {
        return daoCartSql.getTotalSum();
    }

}
