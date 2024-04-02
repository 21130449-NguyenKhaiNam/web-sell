package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Dùng cho việc ghi log.
 * Sử dụng annotation này tại phương thức của interface mà class implement,
 * LogParam - Tại tham số của phương thức Interface để đặt tên khi lấy,
 * do Java phiên bản cũ không có cơ chế lấy,
 * kết hợp với LogTable - Chỉ định tên tại lớp muốn ghi.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD})
public @interface WriteLog {
    int INSERT = 1;
    int UPDATE = 2;
    int SELECT = 3;
    int value();
}
