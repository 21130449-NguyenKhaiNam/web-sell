package services.voucher;

import dao.ShoppingCartDao;
import dao.VoucherDAO;
import dto.VoucherDTO;
import models.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class VoucherServices {
    VoucherDAO voucherDAO = new VoucherDAO();
    private static VoucherServices INSTANCE;
    private ShoppingCartDao shoppingCartDao;

    private VoucherServices() {
        this.shoppingCartDao = new ShoppingCartDao();
    }

    public static VoucherServices getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new VoucherServices();
        }
        return INSTANCE;
    }

    public List<Voucher> getAll() {
        List<Voucher> listVoucher = voucherDAO.selectAll();
        return listVoucher.isEmpty() ? null : listVoucher;
    }

    public List<Voucher> getAll(List<Integer> listIdProduct) {
        List<Voucher> listVoucher = voucherDAO.selectAll(listIdProduct);
        return listVoucher;
    }

    public VoucherDTO canApply(User user, String code, List<Integer> listIdProduct) {
        VoucherDTO voucherDTO = new VoucherDTO();
        if (listIdProduct == null || listIdProduct.isEmpty()) {
            voucherDTO.setState(VoucherApplyState.NOT_FOUND.getValue());
            return voucherDTO;
        }
//        Kiểm tra danh sách sản phẩm gửi lên có nằm trong giỏ hàng của user không ?
        List<CartItem> listCartItem = shoppingCartDao.getCartProductByProductIdAndUserId(listIdProduct, user.getId());
        if (listCartItem == null || listCartItem.isEmpty()) {
            voucherDTO.setState(VoucherApplyState.NOT_FOUND.getValue());
            return voucherDTO;
        }
//        Kiểm tra xem mã giảm giá có tồn tại không?
        Voucher voucher = voucherDAO.selectByCode(code);
        if (voucher == null) {
            voucherDTO.setState(VoucherApplyState.NOT_FOUND.getValue());
            return voucherDTO;
        }
//       Kiểm tra xem voucher còn lượt sử dụng không?
        if (voucher.getAvailableTurns() == 0) {
            voucherDTO.setState(VoucherApplyState.EMPTY_AVAILABLE_TURN.getValue());
            return voucherDTO;
        }

        LocalDate current = LocalDate.now();
        LocalDate givenDate = voucher.getExpiryDate().toLocalDate().plusDays(1);
        if (current.isAfter(givenDate)) {
            voucherDTO.setState(VoucherApplyState.EXPIRED.getValue());
            return voucherDTO;
        }

        VoucherProductStrategy strategy = new VoucherProductStrategy(listCartItem, voucher);
        if (!strategy.apply()) {
            voucherDTO.setState(VoucherApplyState.CAN_NOT_APPLY.getValue());
            return voucherDTO;
        }
        voucherDTO.setVoucher(voucher);
        voucherDTO.setState(VoucherApplyState.CAN_APPLY.getValue());
        voucherDTO.setListIdProduct(listIdProduct);
        return voucherDTO;
    }

    public List<Voucher> getVoucher(Integer start, Integer length, String searchValue, String orderBy, String orderDir) {
        return voucherDAO.selectWithCondition(start, length, searchValue, orderBy, orderDir);
    }

    public long getTotalWithCondition(String searchValue) {
        return voucherDAO.getSizeWithCondition(searchValue);
    }

    public boolean saveVoucher(Voucher voucher, List<Integer> listProductId) {
//        Kiểm tra ngày hết hạn voucher có nhỏ hơn ngày hiện tại không
        Date currentDate = new Date(System.currentTimeMillis());
        if (voucher.getExpiryDate().compareTo(currentDate) < 0) return false;
//        Kiểm tra số lần sử dụng voucher có nhỏ hơn 0 không
        if (voucher.getAvailableTurns() < 0) return false;
//        Kiểm tra phần trăm giảm giá có nhỏ hơn 0 không
        if (voucher.getDiscountPercent() < 0) return false;
//        Kiểm tra giá trị đơn hàng tối thiểu có nhỏ hơn 0 không
        if (voucher.getMinimumPrice() < 0) return false;
//        Kiểm tra mã voucher đã tồn tại chưa
        boolean isExist = voucherDAO.selectByCode(voucher.getCode()) != null;
        if (isExist) return false;
        int voucherIdCreated = voucherDAO.save(voucher);
        voucherDAO.save(voucherIdCreated, listProductId);
        return true;
    }

    public VoucherDTO getDetail(String code) {
        Voucher voucher = voucherDAO.selectByCode(code);
        if (voucher == null) return null;
        VoucherDTO voucherDTO = new VoucherDTO();
        voucherDTO.setVoucher(voucher);
        voucherDTO.setListIdProduct(voucherDAO.getListProductByCode(code));
        return voucherDTO;
    }

    public void changeState(String code, VoucherState type) {
        voucherDAO.changeState(code, type);
    }
}
