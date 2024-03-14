package filter.adminPage;

import models.Category;
import models.Product;
import services.AdminProductServices;
import services.ProductCardServices;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "adminProducts", urlPatterns = { "/adminProducts.jsp", "/filterProductAdmin",
		"/adminProductForm.jsp" })
public class AdminProducts implements Filter {
	private final int LIMIT = 15;
	private final int DEFAULT_PAGE = 1;
    private final int QUANTITY_PAGE_DEFAULT = 5;
    
	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		List<Product> productCardList = AdminProductServices.getINSTANCE().getProducts(DEFAULT_PAGE);
		int quantityPage = AdminProductServices.getINSTANCE().getQuantityPage();
		String requestURL = "/filterProductAdmin?";
		
        int quantityPageMax = QUANTITY_PAGE_DEFAULT;
        if(quantityPageMax > quantityPage) quantityPageMax = quantityPage;
        	
        request.setAttribute("quantityPage", quantityPage);
        request.setAttribute("requestURL", requestURL);
        request.setAttribute("productCardList", productCardList);

        
		request.setAttribute("quantityPageMin", 1);
		request.setAttribute("quantityPageMax", quantityPageMax);
		chain.doFilter(request, response);
	}
}
