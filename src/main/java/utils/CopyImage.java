package utils;

import java.io.*;

public class CopyImage {
    public static void loadImage(File source, File destination) throws IOException {
        if (source.isFile()) {
            copyFile(source.getAbsolutePath(), destination.getAbsolutePath());
        }
        if (source.isDirectory()) {
            // Create the destination folder if it doesn't exist
            if (!destination.exists()) {
                destination.mkdir();
            }

            // List all the files and sub-directories in the source folder
            String[] files = source.list();

            if (files != null) {
                for (String file : files) {
                    File srcFile = new File(source, file);
                    File destFile = new File(destination, file);

                    // Recursively copy sub-folders and files
                    copyFile(srcFile.getAbsolutePath(), destFile.getAbsolutePath());
                }
            }

        }
    }

    public static void copyFile(String sourceFile, String destFile) throws IOException {
        int bufferSize = 1024;
        InputStream fis = new BufferedInputStream(new FileInputStream(sourceFile), bufferSize);
        OutputStream fos = new BufferedOutputStream(new FileOutputStream(destFile), bufferSize);
        int c = fis.read();
        while (c != -1) {
            fos.write(c);
            c = fis.read();
        }
        fis.close();
        fos.close();
        System.out.println("Copy complete");
    }
}
