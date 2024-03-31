package dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD})
@interface Log {
    String INSERT = "1";
    String UPDATE = "2";
    String SELECT = "3";
    String value();
}
