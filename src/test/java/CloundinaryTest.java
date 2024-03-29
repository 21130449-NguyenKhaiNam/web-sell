import services.image.CloudinaryUploadServices;

import javax.servlet.http.Part;
import java.io.*;

public class CloundinaryTest {
    public static void main(String[] args) throws Exception {
        File folder = new File("C:\\Users\\Nam\\Desktop\\LTW_N15\\src\\main\\webapp\\assets\\img\\category");
        for (File f : folder.listFiles()){
            System.out.println(f.getName());
            CloudinaryUploadServices.getINSTANCE().upload("category", f.getName().substring(0, f.getName().lastIndexOf(".")), f);
        }
    }

    public static void copyImageFromFolder(String folderName, String des) throws IOException {
        File folder = new File(folderName);

        for (File file : folder.listFiles()){
            if(file.isDirectory()){
                copyImageFromFolder(file.getCanonicalPath(), des);
            }
            else {
                copyImage(file.getCanonicalPath(), des + "/" + file.getName().substring(0, file.getName().lastIndexOf(".")));
            }
        }
    }

    public static void copyImage(String src, String des) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(des));

        byte[] b = new byte[1024];
        int data;
        while((data = bis.read(b)) != -1){
            bos.write(b);
        }

        bis.close();
        bos.close();
    }


}
