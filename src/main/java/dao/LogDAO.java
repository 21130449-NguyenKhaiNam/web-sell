package dao;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class LogDAO implements InvocationHandler {
    private Object target;

    public LogDAO(Object target) {
        this.target = target;
    }

    public static <T> T createProxy(T obj) {
        return (T) Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                new LogDAO(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(Log.class)) {
            String typeQuery = method.getDeclaredAnnotationsByType(Log.class)[0].value();
            String nameTable = target.getClass().getDeclaredAnnotationsByType(LogTable.class)[0].value();
            if (typeQuery.equals(Log.SELECT)) {
                insertLogTypeSelect(typeQuery, nameTable, args);
            }
        }
        return method.invoke(target, args);
    }

    public void insertLogTypeSelect(String typeQuery, String nameTable, Object[] args) {
        String questions = "";
        for (int i = 0; i < args.length; ++i) {
            questions += "?,";
        }
        questions = questions.substring(0, questions.length() - 1);
        String query = "INSERT INTO Logs (content, id_table) VALUES (" + questions + ")";
        GeneralDao.executeQueryWithSingleTable(query, String.class, args);
    }
}
