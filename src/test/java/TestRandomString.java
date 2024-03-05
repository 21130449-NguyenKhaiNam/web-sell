import java.util.UUID;

public class TestRandomString {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            String randomString = generateRandomString(7);
            System.out.println("Random String: " + randomString);
        }
    }

    public static String generateRandomString(int length) {
        String randomString = UUID.randomUUID().toString().replace("-", "");
        return randomString.substring(0, 7);
    }

}
