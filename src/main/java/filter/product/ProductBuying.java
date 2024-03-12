package filter.product;

import models.Product;
import services.ProductCardServices;

import javax.servlet.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebFilter(filterName = "productBuying", urlPatterns = { "/filterProductBuying", "/productBuying.jsp" })
public class ProductBuying implements Filter {
	private final int QUANTITY_PAGE_DEFAULT = 5;
	private final int DEFAULT_PAGE = 1;
	private int quantityPageTotal;
	private int quantityPageMin;
	private int quantityPageMax;

	public void init(FilterConfig config) throws ServletException {
	}

	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		List<Product> productCardList = ProductCardServices.getINSTANCE().getProducts(DEFAULT_PAGE);
		System.out.println(productCardList);
		request.setAttribute("productCardList", productCardList);
		quantityPageTotal = ProductCardServices.getINSTANCE().getQuantityPage();

		quantityPageMin = 1;
		quantityPageMax = QUANTITY_PAGE_DEFAULT;

		request.setAttribute("quantityPageMin", quantityPageMin);
		request.setAttribute("quantityPageMax", quantityPageMax);
		String requestURL = "/filterProductBuying?";
		request.setAttribute("requestURL", requestURL);
		chain.doFilter(request, response);
	}

}
