package utils;

import models.User;
import services.UserServices;

public class UserFactory {
    public static User getUserById(int userId) {
        return UserServices.getINSTANCE().getUser(userId);
    }

    public static User getUserByIdProductDetail(int orderDetailId) {
        return UserServices.getINSTANCE().getUserByIdProductDetail(orderDetailId);
    }
}
