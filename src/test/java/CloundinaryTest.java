
import models.Product;
import services.admin.AdminProductServices;

import java.io.*;
import java.util.List;

public class CloundinaryTest {
    public static void main(String[] args) {

    }


    private static List<Product> test2(){
        return AdminProductServices.getINSTANCE().getAll();
    }

    public static void copyImageFromFolder(String folderName, String des) throws IOException {
        File folder = new File(folderName);

        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                copyImageFromFolder(file.getCanonicalPath(), des);
            } else {
                copyImage(file.getCanonicalPath(), des + "/" + file.getName().substring(0, file.getName().lastIndexOf(".")));
            }
        }
    }

    public static void copyImage(String src, String des) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(des));

        byte[] b = new byte[1024];
        int data;
        while ((data = bis.read(b)) != -1) {
            bos.write(b);
        }

        bis.close();
        bos.close();
    }


}
