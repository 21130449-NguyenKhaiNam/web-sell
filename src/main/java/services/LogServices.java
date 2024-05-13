package services;

import java.util.Arrays;

// Ghi lại nhật ký thay đổi
public class LogServices {

//    In theo yêu cầu
    private static void print(Object info, String content) {
        System.out.println("Log - " + content + " >> " + info);
    }

//    Ghi nhận khi thêm phần tử
    public synchronized static void insert(Object... info) {
        print(Arrays.toString(info), "insert");
    }

//    Ghi nhận khi thay đổi nội dung
    public synchronized static void update(Object info, Object... objs) {
        print(info, "after data");
        print(Arrays.toString(objs), "data change");
    }

//    Ghi nhận khi gọi lấy phần tử
    public synchronized static void select(Object... info) {
        print(Arrays.toString(info), "select");
    }

}
