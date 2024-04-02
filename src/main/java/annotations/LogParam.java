package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Dùng cho việc chỉ định tên của tham số nhận vào - Do java không hỗ trợ, hoặc phiên bản thấp.
 * Sử dụng tại Interface chứa phương thức
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface LogParam {
    String value();
}
