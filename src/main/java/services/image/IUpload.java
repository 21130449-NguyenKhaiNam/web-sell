package services.image;

import javax.servlet.http.Part;
import java.util.List;

public interface IUpload {
    String getImage(String folderPath, String imageName);

    List<String> getImages(String folderPath, String[] imageNameArray);

    void uploadImage(String folderName, String imageName, Part part) throws Exception;

    void uploadImages(String folderName, String imageName, Part[] parts) throws Exception;

    void createFolder(String folderName) throws Exception;
}
