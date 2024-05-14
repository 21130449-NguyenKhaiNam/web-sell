import services.qr.VietQRImp;

public class TestBankAPI {
    public static void main(String[] args) {
        VietQRImp vietQRImp = new VietQRImp();
        try {
            System.out.println(vietQRImp.getBinBank("BIDV"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
