package dao;

import services.LogServices;

/**
 * Dành cho những lớp muốn ghi lại sự thay đổi thì implement
 * @param <U>
 */
public interface IDao<U> {
//    Ghi lại sự thay đổi về nội dung
    default void update(U info, Object... objs) {
        LogServices.update(info, objs);
    }
//    Ghi lại sự thay đổi về số lượng
    default void insert(U... info) {
        LogServices.insert(info);
    }
//    Ghi lại sự kiện lấy phần tử
    default void select(U... info) {
        LogServices.select(info);
    }
}
