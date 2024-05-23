import services.image.CloudinaryUploadServices;

public class CloudinaryTest {

    public static void main(String[] args) throws Exception {
//    Lấy ảnh
      String linkImg=  CloudinaryUploadServices.getINSTANCE().getImage("product_img/1", "product1.jpg.jpg");
        System.out.println(linkImg);
    }
}
