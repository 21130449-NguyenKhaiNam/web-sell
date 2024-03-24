package services.image;

import javax.servlet.http.Part;
import java.util.List;

//Interface dùng để quản lý các services upload ảnh
//Mở rộng các services upload ảnh khác nhau, nếu
public interface IUpload {
    String getImage(String imageName);

    List<String> getImages(String[] imageNameArray);

    void uploadImage(String imageName, Part part) throws Exception;

    void uploadImages(String imageName, Part[] parts) throws Exception;

    void createFolder() throws Exception;
}