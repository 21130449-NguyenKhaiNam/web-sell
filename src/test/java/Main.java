import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
	public static void main(String[] args) throws IOException {
		String src = "C:/Users/Nam/Desktop/LTW_N15/src/main/webapp/assets/img/product_img";
		String des = "C:/Users/Nam/Desktop/product_img";

		copyFolder(src, des);
	}

	private static void copyFile(String src, String des) throws IOException {
		File sFile = new File(src);

		if (!sFile.exists()) {
			System.out.println("khong tim thay file nguon");
			return;
		}

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(des + "/" + sFile.getName()));

		byte[] b = new byte[1024];
		int data;

		while ((data = bis.read(b)) != -1) {
			bos.write(b, 0, data);
		}

		bis.close();
		bos.close();

		System.out.println("File is copied");
	}

	private static void copyFolder(String src, String des) throws IOException {
		File sFolder = new File(src);
		File dFolder = new File(des);

		if (!sFolder.exists()) {
			System.out.println("khong tim thay folder nguon");
			return;
		} else if (!sFolder.isDirectory()) {
			System.out.println("nguon khong phai thu muc");
			return;
		}

		File[] files = sFolder.listFiles();
		for (File file : files) {
			String sFile = file.getCanonicalPath();
			if (file.isDirectory()) {
				File newDFile = new File(des + "/" + file.getName());
				newDFile.mkdir();
				copyFolder(sFile, newDFile.getCanonicalPath());
			} else {
				copyFile(sFile, des);
			}
		}

		System.out.println("Folder is copied");
	}
}
