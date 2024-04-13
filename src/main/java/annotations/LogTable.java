package annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Dùng cho việc đặt tên nhóm của bảng khi ghi xuống database.
 * Sử dụng bằng cách ghi thêm vào tại lớp muốn ghi xuống cơ sở dữ liệu
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LogTable {
    int USER = 1;
    int PRODUCT = 2;
    int COLOR = 3;
    int CONTACT = 4;
    int DASHBOARD = 5;
    int ADMIN = 6;
    int ORDER = 7;
    int VOUCHER = 8;

    int value();
}
