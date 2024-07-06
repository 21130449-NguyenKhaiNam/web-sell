package database;

import org.jdbi.v3.core.Handle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ConnectionPool implements IConnectionPool {
    private static ConnectionPool INSTANCE;
    private LinkedList<Handle> pool;
    private Map<Handle, Boolean> handleStatus;
    private final int MAX_POOL_SIZE = 10;

    private ConnectionPool() {
        this.pool = new LinkedList<>();
        handleStatus = new HashMap<>();
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            Handle handle = JDBIConnector.get().open();
            pool.addLast(handle);
            handleStatus.put(handle, false);
        }
    }

    public static ConnectionPool getINSTANCE() {
        if (INSTANCE == null) {
            synchronized (ConnectionPool.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPool();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public synchronized Handle getHandle() {
        long startTime = System.currentTimeMillis();
        long waitTime = 10000; // 10 seconds
        while (pool.isEmpty()) {
            try {
                wait(waitTime);
                if (System.currentTimeMillis() - startTime >= waitTime) {
                    throw new RuntimeException("Timeout waiting for database connection.");
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Handle handle = pool.removeFirst();
        handleStatus.put(handle, true);
        return handle;
    }

    @Override
    public synchronized void releaseHandle(Handle handle) {
        if (handle != null && handleStatus.get(handle)) {
            pool.addLast(handle);
            handleStatus.put(handle, false);
            notifyAll();
        } else {
            throw new IllegalArgumentException("Handle not part of this pool or already released.");
        }
    }
}