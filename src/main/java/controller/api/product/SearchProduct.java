package controller.api.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import models.Product;
import services.AdminProductServices;
import utils.ProductFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "searchProduct", value = "/searchProduct")
public class SearchProduct extends HttpServlet {
    // Giới hạn số lượng trả về
    private final int limit = 8;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        if(keyword == null)
            keyword = "";
        List<Integer> products = AdminProductServices.getINSTANCE().getProductByName(keyword).subList(0, limit);
        List<String> nameProducts = new ArrayList<>();
        products.forEach(id -> {
            nameProducts.add(ProductFactory.getProductById(id).getName());
        });
        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = null;
        try {
            jsonResponse = mapper.writeValueAsString(nameProducts);
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            resp.getWriter().write(jsonResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
