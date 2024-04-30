package dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD})
@interface LogInsert {
    static final String INSERT = "insert";
    static final String UPDATE = "update";
    static final String SELECT = "select";
    String value();
}
