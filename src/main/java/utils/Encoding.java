package utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;

public class Encoding {
    private static Encoding INSTANCE;
    private static final String SALT = "agsdddasdasdagsddasdwww";

    private Encoding() {
    }

    public static Encoding getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Encoding();
        }
        return INSTANCE;
    }

    public String toSHA1(String str) {
        String result = null;
        str += SALT;
        try {
            byte[] bytes = str.getBytes();
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            result = Base64.encodeBase64String(md.digest(bytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
