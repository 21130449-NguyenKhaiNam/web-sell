package services;

import dao.ProductCardDAO;
import dao.ProductDao;
import dao.VoucherDAO;
import models.AbstractModel;
import models.Product;
import models.Voucher;
import models.VoucherProduct;

import java.util.List;
import java.util.stream.Collectors;

public class VoucherServices {
    VoucherDAO dao = new VoucherDAO();
    ProductDao productDao = new ProductDao();
    ProductCardDAO productCardDAO = new ProductCardDAO();

    public List<Voucher> getAll() {
        List<Voucher> listVoucher = dao.selectAll();
        return listVoucher.isEmpty() ? null : listVoucher;
    }

//    public boolean canApply(Integer code, List<Integer> cartItem) {
//        Voucher voucher = dao.selectByCode(code);
//        if (voucher == null) return false;
//        cartItem = exportCartItems(voucher.getId(), cartItem);
//        if (cartItem == null) return false;
//        double totalPrice = 0;
//        for (CartItemDTO item : cartItem) {
//            Product product = productDao.getProductByProductId(item.getProductId());
//            double currentPrice = product.getSalePrice() == 0 ? product.getOriginalPrice() : product.getSalePrice();
//            double priceSize = productDao.getPriceSizeByName(item.getSize(), item.getProductId());
//            totalPrice += (currentPrice + priceSize) * item.getQuantity();
//        }
//        return totalPrice >= voucher.getMinimumPrice();
//    }
//
//    private List<AbstractModel> exportCartItems(Integer voucherId, List<Integer> cartItem) {
//        List<VoucherProduct> voucherProduct = dao.selectProductByVoucher(voucherId);
//        List<AbstractModel> cartItem = productCardDAO.getCartItems();
//        if (voucherProduct == null) return null;
//        return cartItem.stream().filter(item -> voucherProduct.stream().anyMatch(product -> product.getProductId().equals(item.getProductId()))).collect(Collectors.toList());
//    }

}
