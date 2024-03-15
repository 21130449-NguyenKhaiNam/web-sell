package services.image;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import properties.CloudinaryProperties;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CloudinaryUpload implements IUpload {
    private static CloudinaryUpload INSTANCE;
    private Cloudinary cloudinary;
    private Integer width;
    private Integer height;
    private Transformation transformation;

    private void init() {
        // Create Cloudinary instance
        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", CloudinaryProperties.getCloudName(),
                "api_key", CloudinaryProperties.getApiKey(),
                "api_secret", CloudinaryProperties.getApiSecret()
        ));
        // Generate transformation if needed
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
        // Generate URL for the image
        String imageUrl = cloudinary.url().transformation(transformation).generate(folderPath + "/" + imageName);

        return imageUrl;
    }

    public List<String> getImage(String folderPath, String[] imageNameArray) {
        return Arrays.stream(imageNameArray).map(imageName ->
                getImage(folderPath, imageName)
        ).collect(Collectors.toList());
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
