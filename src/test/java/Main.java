import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class Main {
	public static void main(String[] args) throws IOException {
		Random rd = new Random();
		for (int i = 170; i <= 218; i++) {
			int indexRandom = rd.nextInt(21);
			if(i == 0) indexRandom = 1;
			String src = "C:\\Users\\Nam\\Desktop\\img\\product" + indexRandom + ".jpg";
			String des = "C:\\Users\\Nam\\Desktop\\LTW_N15\\src\\main\\webapp\\assets\\img\\product_img\\AoJacket\\"+ i;
			copyImage(src, des);
			System.out.println(i);
		}
		System.out.println("done");
	}

	private static void copyImage(String src, String des) throws IOException {
		File sFile = new File(src);
		File dFile = new File(des + "\\" + sFile.getName());
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dFile));

		byte[] b = new byte[1024];
		int data;
		while ((data = bis.read(b)) != -1) {
			bos.write(b);
		}

		sFile.delete();
		bis.close();
		bos.close();
	}

	public static void createFolder(String src, String name) {
		File sFile = new File(src + "\\" + name);
		sFile.mkdir();
	}
}
