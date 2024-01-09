package utils;

import services.UserServices;

public class UserFactory {
    public static String getAvatar(int userId) {
        return UserServices.getINSTANCE().getAvatar(userId);
    }
}
