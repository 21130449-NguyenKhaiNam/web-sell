package filter.home;

import config.ConfigPage;
import models.Product;
import services.HomeServices;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter(filterName = "NewProduct", urlPatterns = {"/public/product/productNew.jsp"})
public class NewProduct implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        List<Product> listAllNewProducts = HomeServices.getINSTANCE().getListNewProducts(true);
        int page = 0, itemsPerPage = 8;
        int size = listAllNewProducts.size();
        int totalPage = (size % itemsPerPage == 0 ? (size / itemsPerPage) : ((size / itemsPerPage)) + 1);

        String xPage = request.getParameter("page");
        if (xPage == null) {
            page = 1;
        } else {
            try {
                page = Integer.parseInt(xPage);
            }catch (NumberFormatException exception){
                exception.printStackTrace();
            }
        }

        int start, end;
        start = (page - 1) * itemsPerPage;
        end = Math.min(page * itemsPerPage, size);
        List<Product> listProductsPerPage = getListProductsPerPage(listAllNewProducts, start, end);

        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("listProductsPerPage", listProductsPerPage);

        chain.doFilter(request, response);
    }

    public List<Product> getListProductsPerPage(List<Product> listProducts, int start, int end) {
        List<Product> listProductsPerPage = new ArrayList<>();
        for (int i = start; i < end; i++) {
            listProductsPerPage.add(listProducts.get(i));
        }
        return listProductsPerPage;
    }
}