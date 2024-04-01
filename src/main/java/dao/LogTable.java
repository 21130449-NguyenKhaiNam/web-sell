package dao;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LogTable {
    int USER = 1;
    int PRODUCT = 2;
    int CHECKOUT = 3;

    int value();
}
