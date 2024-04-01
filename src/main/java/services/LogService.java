package services;

import dao.LogDAO;
import dao.LogTable;
import dao.WriteLog;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogService implements InvocationHandler {
    private static LogService logService;
    private Object target;
    private LogDAO logDAO;

    private LogService() {
        logDAO = new LogDAO();
    }

    public static LogService getINSTANCE() {
        return logService == null ? (logService = new LogService()) : logService;
    }

    public static <T> T createProxy(T obj) {
        LogService.getINSTANCE().setTarget(obj);
        return (T) Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                LogService.getINSTANCE());
    }

    public void setTarget(Object obj) {
        this.target = LogService.getINSTANCE().getTarget() == null ? obj : LogService.getINSTANCE().getTarget();
    }

    public Object getTarget() {
        return this.target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(WriteLog.class)) {
            String typeQuery = method.getDeclaredAnnotationsByType(WriteLog.class)[0].value();
            String nameTable = target.getClass().getDeclaredAnnotationsByType(LogTable.class)[0].value();
            if (typeQuery.equals(WriteLog.SELECT)) {

            }
        }
        return method.invoke(target, args);
    }

}
