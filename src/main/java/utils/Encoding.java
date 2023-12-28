package utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;

public class Encoding {
    private static Encoding INSTANCE;
    private static final String SALT = "agsdddasdasdagsddasdwww";

    private Encoding() {
    }

    public static Encoding getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Encoding();
        }
        return INSTANCE;
    }

    public String toSHA1(String str) {
        String result = null;
        str += SALT;
        try {
            byte[] bytes = str.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public void download(String blobURL, String dest) {
        try {
            URL url = new URL(blobURL);
            InputStream inputStream = url.openStream();

            Path destination = Paths.get(dest);
            Files.createDirectories(destination.getParent());

            FileOutputStream outputStream = new FileOutputStream(destination.toFile());
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }

            // Close streams
            inputStream.close();
            outputStream.close();
            System.out.println("DONE");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Encoding.getINSTANCE().download("blob:http://localhost:8080/e153629b-bda1-4367-8bd7-1f20474a0ee", "D:\\WorkSpace\\image.png");
    }
}
