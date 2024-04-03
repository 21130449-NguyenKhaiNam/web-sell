package services;

import com.fasterxml.jackson.databind.ObjectMapper;
import dao.IDAO;
import dao.ILogDAO;
import dao.LogDAOImp;
import annotations.LogParam;
import annotations.LogTable;
import annotations.WriteLog;
import models.IModel;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.Proxy;
import java.time.LocalDate;
import java.util.*;

public class LogService implements InvocationHandler {
    private static LogService logService;
    private Map<Class<?>, Object> managerTarget;
    private Object target;
    private ILogDAO logDAO;
    private String ip = "128.0.0.1";

    private LogService() {
        logDAO = new LogDAOImp();
        managerTarget = new HashMap<>();
    }

    public static LogService getINSTANCE() {
        return logService == null ? (logService = new LogService()) : logService;
    }

    public <T> T createProxy(T obj) {
        LogService.getINSTANCE().setTarget(obj);
        return (T) Proxy.newProxyInstance(
                obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(),
                LogService.getINSTANCE());
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Object getTarget() {
        return this.target;
    }

    public ILogDAO getLogDAO() {
        return logDAO;
    }

    public void setLogDAO(ILogDAO logDAO) {
        this.logDAO = logDAO;
    }

    public void setTarget(Object obj) {
        if(!managerTarget.containsKey(obj.getClass())) {
            // Hàm put sẽ trả về giá trị trước đó, nếu không có sẽ là null
            managerTarget.put(obj.getClass(), obj);
        }
        this.target = managerTarget.get(obj.getClass());
    }

    /**
     * Không cho phép lấy tên thực tế của tham số,
     * do đó cần sử dụng @LogParam chỉ định tên
     *
     * @param proxy the proxy instance that the method was invoked on
     *
     * @param method the {@code Method} instance corresponding to
     * the interface method invoked on the proxy instance.  The declaring
     * class of the {@code Method} object will be the interface that
     * the method was declared in, which may be a superinterface of the
     * proxy interface that the proxy class inherits the method through.
     *
     * @param args an array of objects containing the values of the
     * arguments passed in the method invocation on the proxy instance,
     * or {@code null} if interface method takes no arguments.
     * Arguments of primitive types are wrapped in instances of the
     * appropriate primitive wrapper class, such as
     * {@code java.lang.Integer} or {@code java.lang.Boolean}.
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.isAnnotationPresent(WriteLog.class)) {
            int level = method.getDeclaredAnnotationsByType(WriteLog.class)[0].value();
            int table = target.getClass().getDeclaredAnnotationsByType(LogTable.class)[0].value();
            Map<String, String> mapObjs = new HashMap<>();
            ObjectMapper mapper = new ObjectMapper();
            for (int i = 0; i < method.getParameterCount(); ++i) {
                Parameter p = method.getParameters()[i];
                String value = p.getDeclaredAnnotationsByType(LogParam.class)[0].value();
                mapObjs.put(value, mapper.writeValueAsString(args[i]));
            }
            IDAO dao = (IDAO) proxy;
            logDAO.writeLog(mapper, dao, args[0], ip, level, mapObjs, LocalDate.now(), table);
        }
        return method.invoke(target, args);
    }

}
