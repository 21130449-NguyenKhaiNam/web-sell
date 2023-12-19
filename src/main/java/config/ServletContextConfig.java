package config;

import utils.MoneyRange;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.ArrayList;
import java.util.List;

@WebListener
public class ServletContextConfig implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        List<MoneyRange> moneyRangeList = new ArrayList<>();
        moneyRangeList.add(new MoneyRange(0, 100000));
        moneyRangeList.add(new MoneyRange(100000, 300000));
        moneyRangeList.add(new MoneyRange(300000, 600000));
        context.setAttribute("moneyRangeList", moneyRangeList);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
    }
}
