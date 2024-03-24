import services.image.CloudinaryUploadServices;

public class CloundinaryTest {
    public static void main(String[] args) {
        String link = CloudinaryUploadServices.getINSTANCE().getImage("12/product12.jpg");

        System.out.println(link);
    }
}
