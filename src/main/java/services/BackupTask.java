package services;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;
import properties.DBProperties;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.UUID;

public class BackupTask implements Runnable {
    private static final GsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    private static final String SERVICE_ACCOUNT_KEY_PATH = getPathToGGCredentials();
    private final static String PATH = "C:\\Users\\AD\\Downloads\\";
    private String name = "backup_" + LocalDate.now() + "_" + UUID.randomUUID() + ".sql";
    //    private String name = "backup_2024-06-22_569f506d-b8bc-4759-a77b-97c6db091fc7.sql";
    private final String BASH = "mysqldump -u " + DBProperties.getUsername() + " " +
            (DBProperties.getPassword().trim().isEmpty() ? "--password=" : ("-p" + DBProperties.getPassword())) + " " +
            DBProperties.getName() + " > " + PATH +
            name;

    public static String getPathToGGCredentials() {
        URL url = BackupTask.class.getClassLoader()
                .getResource("credentials.json");
        String path = url == null ? "" : url.getPath().substring(1);
        return path;
    }

    public static void main(String[] args) throws URISyntaxException {
        BackupTask backupTask = new BackupTask();
        Thread thread = new Thread(backupTask);
        thread.start();
    }

    @Override
    public void run() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("cmd.exe", "/c", BASH);

        try {
            // Khởi chạy tiến trình
            Process process = processBuilder.start();
            System.out.println("CMD >> " + BASH);

            // Đọc kết quả đầu ra (stdout)
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Đọc kết quả đầu ra lỗi (stderr)
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
            }

            // Chờ tiến trình hoàn thành và lấy mã trả về
            int exitCode = process.waitFor();
            System.out.println("\nBackup kết thúc với mã: " + exitCode);
            if (exitCode == 0) {
                java.io.File file = new java.io.File(PATH + name);
                uploadFileToDrive(file);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
    }

    public String uploadFileToDrive(java.io.File file) throws GeneralSecurityException, IOException {
        String folderId = "11YhgmMFS-BgD6BvD_cxjY1ZWq0BAL2wi";
        Drive drive = createDriveService();
        try {
            File fileMeta = new File();
            fileMeta.setName(name);
            fileMeta.setParents(Collections.singletonList(folderId));
            FileContent content = new FileContent("file/sql", file);
            File uploadFile = drive.files().create(fileMeta, content)
                    .setFields("id")
                    .execute();
            String fileUrl = "https://drive.google.com/uc?export=view&id=" + uploadFile.getId();
            System.out.println(fileUrl);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "success";
    }

    public Drive createDriveService() {
        try{
            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(SERVICE_ACCOUNT_KEY_PATH))
                    .createScoped(Collections.singleton(DriveScopes.DRIVE));
            HttpRequestInitializer initializer = new HttpCredentialsAdapter(credentials);
            return new Drive.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(),
                    JSON_FACTORY,
                    initializer)
                    .build();
        } catch (Exception e) {
            System.out.println("BackupTask >> Có vẻ thiếu đường file credentials.json");
            e.printStackTrace();
        }
        return null;
    }

}
