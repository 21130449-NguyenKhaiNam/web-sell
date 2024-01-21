package services;

import utils.Token;

import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UploadImageServices {
    private File rootFolder;
    private List<String> nameImages;

    public UploadImageServices(String root) {
        this.rootFolder = new File(root);
        this.nameImages = new ArrayList<>();
        if (!rootFolder.exists()) rootFolder.mkdirs();
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

    public void addImages(Collection<Part> parts) throws IOException {
        for (Part part : parts) {
            addImage(part);
        }
    }

    public void addImage(Part part) throws IOException {
        if (!isPartImage(part)) return;
        String fileName = Token.generateToken();
        String pathImage = rootFolder.getAbsolutePath() + "/" + fileName + "." + getFileExtension(part);
        part.write(pathImage);
        nameImages.add(fileName + "." + getFileExtension(part));
    }

    public void deleteImages(List<String> nameImages) {
        for (String nameImage : nameImages) {
            File file = new File(rootFolder.getAbsolutePath() + "/" + nameImage);
            if (file.exists() && file.isFile()) {
                file.delete();
                System.out.println("DELETE Success");
            }
        }
    }

    public List<String> getNameImages() {
        return nameImages;
    }

    public void setNameImages(ArrayList<String> nameImages) {
        this.nameImages = nameImages;
    }
}
