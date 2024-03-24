package services.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import properties.CloudinaryProperties;

import javax.servlet.http.Part;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Services dùng để upload ảnh, service hiện tại đang sử dụng Cloudinary làm cloud lưu trữ ảnh
public class CloudinaryUploadServices implements IUpload {
    private static CloudinaryUploadServices INSTANCE = null;
    private final String DEFAUL_FOLDER = "product_img/";
    private final String DEFAULT_EXT = ".jpg";
    private Cloudinary cloudinary;
    // Chiều cao và chiều dài cho ảnh lấy (bên cloud tự điều chỉnh kích thước width và height, nếu để null thì lấy kích thước gốc của ảnh)
    private Integer width;
    private Integer height;
    //    Obj dùng để thao tác trên ảnh
    private Transformation transformation;

    //Khởi tạo các giá trị cần thiết cho biến
    private void init() {
        cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", CloudinaryProperties.getCloudName(), "api_key", CloudinaryProperties.getApiKey(), "api_secret", CloudinaryProperties.getApiSecret(), "access_mode", "public", "secure", true));
        transformation = new Transformation().width(width).height(height).crop("fill");
    }

    private CloudinaryUploadServices() {
        init();
    }

    // Áp dụng Singleton pattern: đảm bảo chỉ có 1 service đang chạy, tránh có nhiều khởi tạo nhiều instances
//Mỗi khi muốn lấy Service upload ảnh chỉ cần gọi Cloudinary.getInstance().method
    public static CloudinaryUploadServices getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CloudinaryUploadServices();
        }
        return INSTANCE;
    }

    //    Lấy 1 link ảnh từ cloudinary
    @Override
    public String getImage(String imageName) {
        return cloudinary.url().transformation(transformation).generate(DEFAUL_FOLDER + imageName + DEFAULT_EXT);
    }

    //    Lấy danh sách link ảnh từ cloudinary
    @Override
    public List<String> getImages(String[] imageNameArray) {
        return Arrays.stream(imageNameArray).map(imageName -> getImage(imageName)).collect(Collectors.toList());
    }

    //    Upload 1 ảnh lên cloudinary
    @Override
    public void uploadImage(String imageName, Part part) throws Exception {
        Map<String, Object> folderParams = ObjectUtils.asMap("folder", DEFAUL_FOLDER);
        cloudinary.api().createFolder(DEFAUL_FOLDER, folderParams);

        File tempFile = File.createTempFile("temp", null);
        part.write(tempFile.getAbsolutePath());
        cloudinary.uploader().upload(tempFile, ObjectUtils.asMap("folder", DEFAUL_FOLDER, "public_id", imageName));
    }

    //    Upload nhiều ảnh lên cloudinary
    @Override
    public void uploadImages(String imageName, Part[] parts) throws Exception {
        for (Part part : parts) {
            uploadImage(imageName, part);
        }
    }

    //    Tạo 1 folder trên cloudinary
    @Override
    public void createFolder() throws Exception {
        Map<String, Object> folderParams = ObjectUtils.asMap("folder", DEFAUL_FOLDER);
        cloudinary.api().createFolder(DEFAUL_FOLDER, folderParams);
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
        transformation.width(this.width);
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
        transformation.height(this.height);
    }
}