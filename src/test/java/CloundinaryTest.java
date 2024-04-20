import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import services.image.CloudinaryUploadServices;

import java.io.*;

public class CloundinaryTest {
    public static void main(String[] args) throws Exception {
//        File file = new File("C:/Users/Nam/Desktop/product3 - Copy.jpg");
//        CloudinaryUploadServices.getINSTANCE().upload("product_img/1", "test_image", file);
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dsyo2digs",
                "api_key", "292588852612766",
                "api_secret", "PrkjaBLF_mV8M9l1dkwQhTV_4ew"));
        String name = "C:/Users/Nam/Desktop/ChuongTrinhDaiHoc/TTLTWeb/product_img/3/product3.jpg";
        String[] a = {name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name, name};
        int total = 0;
        for (int i = 0; i < a.length; i++) {
            Long st = System.currentTimeMillis();
            CloudinaryUploadServices.getINSTANCE().upload("product_img/1", "test " + i, a[i]);
            Long et = System.currentTimeMillis();
            System.out.println(i);
            total += et - st;
        }
        System.out.println(total / a.length);
    }
}
