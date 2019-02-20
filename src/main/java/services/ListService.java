package services;

import dao.DaoCommoditySql;
import dao.DaoUserSql;
import dto.Commodity;
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

public class ListService {

    private Connection connection;
    private DaoCommoditySql daoCommoditySql;
    private final Configuration cfg = new Configuration();
    private final DaoUserSql daoUserSql;


    {
        cfg.setClassForTemplateLoading(this.getClass(), "../templates/listFTL");
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLocale(Locale.US);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
    }


    public ListService(Connection connection) {
        this.connection = connection;
        this.daoCommoditySql = new DaoCommoditySql(connection);
        this.daoUserSql = new DaoUserSql(connection);
    }



    public List<Commodity> getAllCommodities() {
        return daoCommoditySql.getAll();
    }

    public void writeTemplateToFile(int userId) {
        Map<String, Object> input = new HashMap<>();
        input.put("title", "Shop");
        input.put("exampleObject", new Commodity("iPhone", 900, 4));
        input.put("user", daoUserSql.get(userId));
        input.put("items", getAllCommodities());
        try (Writer fileWriter = new FileWriter(new File("src/main/resources/templates/listHTML/list.html"))) {
            Template template = cfg.getTemplate("list.ftl");
            template.process(input, fileWriter);
        } catch (TemplateException | IOException e) {
            throw new IllegalStateException("Something went wrong");
        }
    }
}
