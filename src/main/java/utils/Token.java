package utils;

import java.util.UUID;

public class Token {
    public static String generateToken(){
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }
}
