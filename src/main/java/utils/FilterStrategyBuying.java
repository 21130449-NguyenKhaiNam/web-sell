package utils;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import models.Image;
import models.Product;
import services.ProductCardServices;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FilterStrategyBuying extends FilterStrategy {
    private final int QUANTITY_PAGE_DEFAULT = 5;
    private int quantityPageMin;
    private int quantityPageMax;
    private int currentPage;

    public FilterStrategyBuying(HttpServletRequest request) {
        super(request);
    }

    public FilterStrategyBuying(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void doFilter() {
        List<Integer> filterByColor = filterByColor();
        List<Integer> filterByCategoryId = filterByCategory();
        List<Integer> filterByMoneyRange = filterMyMoney();
        List<Integer> filterBySize = filterBySize();
        String pageNumber = request.getParameter("page");

        int page;
        try {
            page = Integer.parseInt(pageNumber);
        } catch (NumberFormatException e) {
            page = 1;
        }
        List<List<Integer>> listId = new ArrayList<>();
        listId.add(filterByColor);
        listId.add(filterByCategoryId);
        listId.add(filterByMoneyRange);
        listId.add(filterBySize);
        List<Integer> listIDFiltered = findCommonIDs(listId);
        List<Product> productCardFiltered = ProductCardServices.getINSTANCE().filter(listIDFiltered.isEmpty() ? null : listIDFiltered, page);

        int quantityPage = productCardFiltered.isEmpty() ? 0 : ProductCardServices.getINSTANCE().getQuantityPage(listIDFiltered);

        StringBuffer requestURL = request.getRequestURL();
        String queryString = request.getQueryString();
        queryString = cutParameterInURL(queryString, "page");
        requestURL.append("?").append(queryString);

        List<String> listInputChecked = listValueChecked(queryString);

        List<DetailProduct> detailProducts = new ArrayList<>();
        productCardFiltered.forEach(product -> {
            DetailProduct dp = new DetailProduct(product,
                    ProductFactory.getListImagesByProductId(product.getId()),
                    ProductFactory.calculateStar(product.getId()),
                    ProductFactory.getReviewCount(product.getId()));
            detailProducts.add(dp);
        });
        FilteredProductResponse responseData = new FilteredProductResponse(requestURL.toString(), detailProducts, quantityPage, page, listInputChecked);

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse = null;
        try {
            jsonResponse = mapper.writeValueAsString(responseData);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(jsonResponse);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private class DetailProduct {
        private Product product;
        private List<Image> imgs;
        private int stars;
        private int reviewCounts;

        public DetailProduct(Product product, List<Image> imgs, int stars, int reviewCounts) {
            this.product = product;
            this.imgs = imgs;
            this.stars = stars;
            this.reviewCounts = reviewCounts;
        }

        public int getStars() {
            return stars;
        }

        public void setStars(int stars) {
            this.stars = stars;
        }

        public int getReviewCounts() {
            return reviewCounts;
        }

        public void setReviewCounts(int reviewCounts) {
            this.reviewCounts = reviewCounts;
        }

        @JsonGetter("product")
        public Product getProduct() {
            return product;
        }

        public void setProduct(Product product) {
            this.product = product;
        }

        @JsonGetter("images")
        public List<Image> getImgs() {
            return imgs;
        }

        public void setImgs(List<Image> imgs) {
            this.imgs = imgs;
        }
    }

    private class FilteredProductResponse {
        private String requestURL;
        private List<DetailProduct> productCardFiltered;
        private int quantityPage;
        private int page;
        private List<String> listInputChecked;

        public FilteredProductResponse(String requestURL,
                                       List<DetailProduct> productCardFiltered,
                                       int quantityPage,
                                       int page,
                                       List<String> listInputChecked) {
            this.requestURL = requestURL;
            this.productCardFiltered = productCardFiltered;
            this.quantityPage = quantityPage;
            this.page = page;
            this.listInputChecked = listInputChecked;
        }

        @JsonGetter("url")
        public String getRequestURL() {
            return requestURL;
        }

        public void setRequestURL(String requestURL) {
            this.requestURL = requestURL;
        }

        @JsonGetter("products")
        public List<DetailProduct> getProductCardFiltered() {
            return productCardFiltered;
        }

        public void setProductCardFiltered(List<DetailProduct> productCardFiltered) {
            this.productCardFiltered = productCardFiltered;
        }

        @JsonGetter("quantity")
        public int getQuantityPage() {
            return quantityPage;
        }

        public void setQuantityPage(int quantityPage) {
            this.quantityPage = quantityPage;
        }

        @JsonGetter("page")
        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        @JsonGetter("inputsChecked")
        public List<String> getListInputChecked() {
            return listInputChecked;
        }

        public void setListInputChecked(List<String> listInputChecked) {
            this.listInputChecked = listInputChecked;
        }
    }
}
