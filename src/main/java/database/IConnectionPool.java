package database;

import org.jdbi.v3.core.Handle;

public interface IConnectionPool {
    public  Handle getHandle();

    public void releaseHandle(Handle handle);
}
