package utils;

import services.ProductCardServices;
import models.Color;
import models.Image;
import models.Product;
import models.Size;
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
}