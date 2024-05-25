import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CloudinaryTest {

    private static final String vnp_TmnCode = "H1B0VPW2";
    private static final String vnp_HashSecret = "8VXRGEO0P8AOXW3BT40FG2HSKB6JYQOT";

    public static void main(String[] args) throws UnsupportedEncodingException {
        String vnp_Url = "https://sandbox.vnpayment.vn/paymentv2/vpcpay.html";
        String vnp_Returnurl = "http://localhost:8080/public/user/vnpReturn.jsp";
        String vnp_TxnRef = "49473311";
        String vnp_OrderInfo = "Thanh toan don hang:" + vnp_TxnRef;
        String vnp_OrderType = "other";
        String vnp_Amount = "10000"; // Số tiền thanh toán
        String vnp_Locale = "vn";
        String vnp_IpAddr = "0:0:0:0:0:0:0:1";
        String vnp_CreateDate = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String vnp_ExpireDate = "20240517151224";

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", "2.1.0");
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(Integer.parseInt(vnp_Amount) * 100));
        vnp_Params.put("vnp_Command", "pay");
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);
        vnp_Params.put("vnp_Locale", vnp_Locale);
        vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
        vnp_Params.put("vnp_OrderType", vnp_OrderType);
        vnp_Params.put("vnp_ReturnUrl", vnp_Returnurl);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);

        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        for (String fieldName : fieldNames) {
            String fieldValue = vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(fieldValue);
                query.append(URLEncoder.encode(fieldName, "UTF-8"));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, "UTF-8"));
                if (fieldNames.indexOf(fieldName) != fieldNames.size() - 1) {
                    hashData.append('&');
                    query.append('&');
                }
            }
        }
        String vnp_SecureHash = Sha256(vnp_HashSecret + hashData.toString());
        query.append("&vnp_SecureHashType=SHA256&vnp_SecureHash=");
        query.append(URLEncoder.encode(vnp_SecureHash, "UTF-8"));

        String paymentUrl = vnp_Url + "?" + query.toString();
        System.out.println(paymentUrl);
    }

    public static String Sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] array = md.digest(input.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte item : array) {
                sb.append(String.format("%02x", item));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
