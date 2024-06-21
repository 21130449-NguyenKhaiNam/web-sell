package services.mail;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public interface ThreadMail {
    ExecutorService executorService = Executors.newFixedThreadPool(10);
}
