package controller.api.admin.product;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import config.ConfigPage;
import models.Product;
import properties.PathProperties;
import services.ProductServices;
import services.admin.AdminCategoryServices;
import services.admin.AdminProductServices;
import services.image.CloudinaryUploadServices;
import utils.ProductFactory;
import utils.Token;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@WebServlet(name = "admin-import-product", value = "/admin-import-product")
@MultipartConfig(fileSizeThreshold = 1024 * 12024, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 100)
public class ImportProduct extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy file từ request
        Part filePart = request.getPart("file");

        // Tạo một InputStream để đọc dữ liệu của file
        InputStream fileContent = filePart.getInputStream();

        try (InputStreamReader reader = new InputStreamReader(fileContent);
             CSVReader csvReader = new CSVReader(reader)) {
            csvReader.skip(1);
            String[] nextRecord;

            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.println(Arrays.toString(nextRecord));
                String size = nextRecord[5];
                String codeColor = nextRecord[6];
                String img = nextRecord[7];
                String priceSize = nextRecord[8];

                String name = nextRecord[0];
                int categoryId = Integer.parseInt(AdminCategoryServices.getINSTANCE().getIdByNameType(nextRecord[1]) + "");
                String des = nextRecord[2];
                double originalPrice = Double.parseDouble(nextRecord[3]);
                double salePrice = Double.parseDouble(nextRecord[4]);

                Product newProduct = new Product();
                newProduct.setName(name);
                newProduct.setCategoryId(categoryId);
                newProduct.setDescription(des);
                newProduct.setOriginalPrice(originalPrice);
                newProduct.setSalePrice(salePrice);
                newProduct.setVisibility(true);
                newProduct.setCreateAt(Date.valueOf(LocalDate.now()));


                // nếu chưa tồn tại
                if (ProductServices.getINSTANCE().getProductByMultipleParam(name, categoryId, des, originalPrice, salePrice) == null) {
                    AdminProductServices.getINSTANCE().addProduct(newProduct);
                    int maxId = ProductFactory.getMaxId().getId();

                    // thêm size
                    String[] sizes = {size};
                    double[] priceSizes = {Double.parseDouble(priceSize)};
                    AdminProductServices.getINSTANCE().addSize(sizes, priceSizes, maxId);
                    // thêm color
                    String[] codeColors = {codeColor};
                    AdminProductServices.getINSTANCE().addColor(codeColors, maxId);

                    // thêm img
                    String nameImage = img.substring(img.lastIndexOf("/") + 1);
                    List<String> listImage = new ArrayList<>();
                    listImage.add(maxId + "/" + nameImage);

                    AdminProductServices.getINSTANCE().addImages(listImage, maxId);

                    //thêm lên cloud
                    CloudinaryUploadServices.getINSTANCE().upload("product_img/" + maxId, nameImage.substring(0, nameImage.lastIndexOf(".")), img);
                } else {
                    // nếu sản phẩm đã tồn tại
                    // thì lấy ra id với điều kiện tên
                    int idAvailable = ProductServices.getINSTANCE().getProductByMultipleParam(name, categoryId, des, originalPrice, salePrice).getId();

                    // nếu size chưa tồn tại
                    // tham số là size và productId
                    if (ProductServices.getINSTANCE().getSizeByNameSizeWithProductId(size, idAvailable) == null) {
                        // thêm size
                        String[] sizes = {size};
                        double[] priceSizes = {Double.parseDouble(priceSize)};
                        AdminProductServices.getINSTANCE().addSize(sizes, priceSizes, idAvailable);
                    }

                    // nếu color chưa tồn tại
                    // tham số là codeColor và productId
                    if (ProductServices.getINSTANCE().getColorByCodeColorWithProductId(codeColor, idAvailable) == null) {
                        // thêm color
                        String[] codeColors = {codeColor};
                        AdminProductServices.getINSTANCE().addColor(codeColors, idAvailable);
                    }


                    // nếu image chưa tồn tại
                    // tham số là nameImage và productId
                    String nameImage = img.substring(img.lastIndexOf("/") + 1);
                    if (ProductServices.getINSTANCE().getImageByNameImageWithProductId(idAvailable +"/" +nameImage, idAvailable) == null) {
                        // thêm img
                        List<String> listImage = new ArrayList<>();
                        listImage.add(idAvailable + "/" + nameImage);
                        AdminProductServices.getINSTANCE().addImages(listImage, idAvailable);

                        //thêm lên cloud
                        CloudinaryUploadServices.getINSTANCE().upload("product_img/" + idAvailable, nameImage.substring(0, nameImage.lastIndexOf(".")), img);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher(ConfigPage.ADMIN_PRODUCT).forward(request, response);
    }


}