package logging;

import models.Log;

public class ThreadSharing {
    private static ThreadLocal<Log> logThreadLocal = new ThreadLocal<>();

    public static Log get() {
        return logThreadLocal.get();
    }

    public static void set(Log log) {
        logThreadLocal.set(log);
    }

    public static void remove() {
        logThreadLocal.remove();
    }
}
