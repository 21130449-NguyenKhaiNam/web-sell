package services.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import properties.CloudinaryProperties;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CloudinaryUpload implements IUpload {
    private static CloudinaryUpload INSTANCE = null;
    private Cloudinary cloudinary;
    private Integer width;
    private Integer height;
    private Transformation transformation;

    private void init() {
        cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", CloudinaryProperties.getCloudName(), "api_key", CloudinaryProperties.getApiKey(), "api_secret", CloudinaryProperties.getApiSecret(), "access_mode", "public"));
        transformation = new Transformation().width(width).height(height).crop("fill");
    }

    private CloudinaryUpload() {
        init();
    }

    public static CloudinaryUpload getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CloudinaryUpload();
        }
        return INSTANCE;
    }

    @Override
    public String getImage(String folderPath, String imageName) {
        return cloudinary.url().transformation(transformation).generate(folderPath + "/" + imageName);
    }

    @Override
    public List<String> getImages(String folderPath, String[] imageNameArray) {
        return Arrays.stream(imageNameArray).map(imageName -> getImage(folderPath, imageName)).collect(Collectors.toList());
    }


    @Override
    public void uploadImage(String folderName, String imageName, Part part) throws Exception {
        Map<String, Object> folderParams = ObjectUtils.asMap("folder", folderName);
        cloudinary.api().createFolder(folderName, folderParams);

        File tempFile = File.createTempFile("temp", null);
        part.write(tempFile.getAbsolutePath());
        cloudinary.uploader().upload(tempFile, ObjectUtils.asMap("folder", folderName, "public_id", imageName));
    }

    @Override
    public void uploadImages(String folderName, String imageName, Part[] parts) throws Exception {
        for (Part part : parts) {
            uploadImage(folderName, imageName, part);
        }
    }

    public void uploadImage(String folderName, String imageName, File file) throws Exception {
        Map<String, Object> folderParams = ObjectUtils.asMap("folder", folderName);
        cloudinary.api().createFolder(folderName, folderParams);

        cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", folderName, "public_id", imageName));
    }

    @Override
    public void createFolder(String folderName) throws Exception {
        Map<String, Object> folderParams = ObjectUtils.asMap("folder", folderName);
        cloudinary.api().createFolder(folderName, folderParams);
    }

    public void uploadFolder(File folder, String cloudinaryFolder) throws IOException {
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                String subCloudinaryFolder = cloudinaryFolder + "/" + file.getName();
                createCloudinaryFolder(subCloudinaryFolder);
                uploadFolder(file, subCloudinaryFolder); // Recursive call for subdirectories
            } else {
                // Upload file to Cloudinary
                Map<?, ?> result = cloudinary.uploader().upload(file, ObjectUtils.asMap("folder", cloudinaryFolder));
                System.out.println("Uploaded: " + result.get("secure_url"));
            }
        }
    }

    private void createCloudinaryFolder(String folderName) throws IOException {
        cloudinary.uploader().upload(ObjectUtils.emptyMap(), ObjectUtils.asMap("folder", folderName));
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
