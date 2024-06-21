package services;

import properties.DBProperties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@WebListener
public class BackupService implements ServletContextListener {
    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        scheduler = Executors.newScheduledThreadPool(1);

        // Thiết lập lịch trình chạy backup sau mỗi 24 giờ (ví dụ)
        scheduler.scheduleAtFixedRate(new BackupTask(), 0, 1, TimeUnit.MINUTES);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Thực hiện các thao tác dọn dẹp khi ServletContext bị hủy
        scheduler.shutdown();
    }

    class BackupTask implements Runnable {
        private final String BASH = "mysqldump -u " + DBProperties.getUsername() + " " + (DBProperties.getPassword().trim().isEmpty() ? "--password=" : ("-p" + DBProperties.getPassword())) + " " + DBProperties.getName() + " > " + " C:\\Users\\AD\\Downloads\\test_backup_" + UUID.randomUUID() + ".sql";

        @Override
        public void run() {
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd.exe", "/c", BASH);

            System.out.println("CMD >> " + BASH);

            try {
                // Khởi chạy tiến trình
                Process process = processBuilder.start();

                // Đọc kết quả đầu ra
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }

                // Chờ tiến trình hoàn thành và lấy mã trả về
                int exitCode = process.waitFor();
                System.out.println("\nLệnh kết thúc với mã: " + exitCode);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}