package services.image;

import utils.Token;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UploadImageServices {
    private String ROOT_FOLDER;
    private List<String> nameImages;

    public UploadImageServices(String root) {
        this.ROOT_FOLDER = root;
        this.nameImages = new ArrayList<>();
    }

    public static boolean isPartImage(Part part) {
        if (part != null) {
            String contentType = part.getContentType();
            if (contentType != null && contentType.startsWith("image/")) {
                return true;
            }
        }
        return false;
    }

    private String getFileExtension(Part part) {
        String fileExtension = part.getSubmittedFileName().substring(part.getSubmittedFileName().lastIndexOf(".") + 1);
        return fileExtension;
    }

    public void addImages(Collection<Part> parts) throws Exception {
        for (Part part : parts) {
            addImage(part);
        }
    }

    public void addImage(Part part) throws Exception {
        if(isPartImage(part)){
            String root = ROOT_FOLDER.substring(0, ROOT_FOLDER.lastIndexOf("/"));
            String idCategory = ROOT_FOLDER.substring(ROOT_FOLDER.lastIndexOf("/")+1, ROOT_FOLDER.length());
            String imageName = idCategory + "/" + getFileName(part);

            nameImages.add(imageName);
            CloudinaryUploadServices.getINSTANCE().uploadImage(root, imageName.substring(0, imageName.lastIndexOf(".")), part);
        }
    }

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");

        String[] tokens = contentDisp.split(";");
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return "";
    }

    public void deleteImage(String imageName) throws IOException {
        CloudinaryUploadServices.getINSTANCE().deleteImage(imageName);
    }
    public void deleteImages(List<String> nameImages) throws IOException {
        for (String nameImage : nameImages) {
            nameImage = nameImage.substring(0, nameImage.lastIndexOf("."));
            deleteImage(nameImage);
        }
    }

    public List<String> getNameImages() {
        return nameImages;
    }

    public void setNameImages(ArrayList<String> nameImages) {
        this.nameImages = nameImages;
    }
}
