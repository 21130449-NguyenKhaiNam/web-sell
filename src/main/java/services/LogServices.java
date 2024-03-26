package services;

import dao.DAO;
import dao.IDao;

import java.util.Arrays;

// Ghi lại nhật ký thay đổi
public class LogServices {

//    In theo yêu cầu
    private static <U extends Object> void print(U info, String content) {
        System.out.println("Log - " + content + " >> " + info);
    }

//    Ghi nhận khi thêm phần tử
    public static <U extends IDao> void insert(U... info) {
        print(Arrays.toString(info), "insert");
    }

//    Ghi nhận khi thay đổi nội dung
    public static <U extends IDao> void update(U info, Object... objs) {
        print(info, "after data");
        print(Arrays.toString(objs), "data change");
    }

//    Ghi nhận khi gọi lấy phần tử
    public static <U extends IDao> void select(U... info) {
        print(Arrays.toString(info), "select");
    }

}
