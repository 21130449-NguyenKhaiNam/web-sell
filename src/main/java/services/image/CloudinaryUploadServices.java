package services.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import properties.CloudinaryProperties;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//Services dùng để upload ảnh, service hiện tại đang sử dụng Cloudinary làm cloud lưu trữ ảnh
public class CloudinaryUploadServices implements IUpload {
    private static CloudinaryUploadServices INSTANCE = null;
    private Cloudinary cloudinary;
    // Chiều cao và chiều dài cho ảnh lấy (bên cloud tự điều chỉnh kích thước width và height, nếu để null thì lấy kích thước gốc của ảnh)
    private Integer width;
    private Integer height;
    //    Obj dùng để thao tác trên ảnh
    private Transformation transformation;

    //Khởi tạo các giá trị cần thiết cho biến
    private void init() {
        cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", CloudinaryProperties.getCloudName(), "api_key", CloudinaryProperties.getApiKey(), "api_secret", CloudinaryProperties.getApiSecret(), "access_mode", "public", "secure", true));
        transformation = new Transformation().crop("scale").chain()
                .quality("auto").chain()
                .fetchFormat("auto");
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
    public String getImage(String folderPath, String imageName) {
        return cloudinary.url().transformation(transformation).generate(folderPath + "/" + imageName);
    }

    //    Lấy danh sách link ảnh từ cloudinary
    @Override
    public List<String> getImages(String folderPath, List<String> imageNameArray) {
        List<String> res = new ArrayList<>();

        for(String imageName : imageNameArray){
            res.add( getImage(folderPath, imageName));
        }
        return res;
    }

    //    Upload 1 ảnh lên cloudinary
    @Override
    public void uploadImage(String folderName, String imageName, Part part) throws Exception {
        Map<String, Object> folderParams = ObjectUtils.asMap("folder", folderName);
        cloudinary.api().createFolder(folderName, folderParams);

        File tempFile = File.createTempFile("temp", null);
        part.write(tempFile.getAbsolutePath());
        cloudinary.uploader().uploadLarge(tempFile, ObjectUtils.asMap("folder", folderName, "public_id", imageName));
    }

    public void upload(String folderName, String imageName, String file) throws Exception {
        Map<String, Object> folderParams = ObjectUtils.asMap("folder", folderName);
        cloudinary.api().createFolder(folderName, folderParams);

        cloudinary.uploader().uploadLarge(file, ObjectUtils.asMap("folder", folderName, "public_id", imageName));
    }

    //    Upload nhiều ảnh lên cloudinary
    @Override
    public void uploadImages(String folderName, String imageName, Part[] parts) throws Exception {
        for (Part part : parts) {
            uploadImage(folderName, imageName, part);
        }
    }

    public void deleteImage(String imageFolder) throws IOException {
        cloudinary.uploader().destroy(imageFolder, ObjectUtils.emptyMap());
    }

    public void deleteImages(List<String> imagesFolder) throws IOException {
        for (String imageFolder : imagesFolder){
            cloudinary.uploader().destroy(imageFolder, ObjectUtils.emptyMap());
        }
    }

    //    Tạo 1 folder trên cloudinary
    @Override
    public void createFolder(String folderName) throws Exception {
        Map<String, Object> folderParams = ObjectUtils.asMap("folder", folderName);
        cloudinary.api().createFolder(folderName, folderParams);
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