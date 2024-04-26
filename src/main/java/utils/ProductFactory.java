package utils;

import models.*;
import services.admin.AdminReviewServices;
import services.ProductCardServices;
import services.ProductServices;

import java.util.List;

public class ProductFactory {

    public static Product getProductById(int productId){
        return ProductServices.getINSTANCE().getProductByProductId(productId);
    }

    public static List<Image> getListImagesByProductId(int productId){
        return ProductServices.getINSTANCE().getListImagesByProductId(productId);
    }

    public static List<Color> getListColorsByProductId(int productId){
        return ProductServices.getINSTANCE().getListColorsByProductId(productId);
    }

    public static List<Size> getListSizesByProductId(int productId){
        return ProductServices.getINSTANCE().getListSizesByProductId(productId);
    }

    public static double getPriceSizeByName(String nameSize, int productId){
        return ProductServices.getINSTANCE().getPriceSizeByName(nameSize, productId);
    }

    public static int getReviewCount(int productId) {
        return ProductCardServices.getINSTANCE().getReviewCount(productId);
    }
    public static int calculateStar(int productId) {
        return ProductCardServices.getINSTANCE().calculateStar(productId);
    }
    public static Size getSizeByNameSizeWithProductId(String nameSize, int productId){
        return ProductServices.getINSTANCE().getSizeByNameSizeWithProductId(nameSize, productId);
    }

    public static Color getColorByCodeColorWithProductId(String codeColor, int productId){
        return ProductServices.getINSTANCE().getColorByCodeColorWithProductId(codeColor, productId);
    }

    public static String getNameCategoryById(int id) {
        return ProductCardServices.getINSTANCE().getNameCategoryById(id);
    }

    public static User getUserByIdOrderDetail(int orderDetailId) {
        return AdminReviewServices.getINSTANCE().getUserByIdProductDetail(orderDetailId);
    }

    public static String getNameProductByIdOrderDetail(int orderDetailId) {
        return ProductCardServices.getINSTANCE().getNameProductByIdOrderDetail(orderDetailId);
    }

    public static Product getMaxId(){
        return ProductServices.getINSTANCE().getMaxId();
    }

}
