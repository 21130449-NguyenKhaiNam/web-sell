import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import services.image.CloudinaryUpload;

public class CloudinaryTest {

    public static void main(String[] args) {
        System.out.println(CloudinaryUpload.getINSTANCE().getImage("slider",
                "cjrshzi1fgiz6gbfjsmb.webp"));
    }
}
