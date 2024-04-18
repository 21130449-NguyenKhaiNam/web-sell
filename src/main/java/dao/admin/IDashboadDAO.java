package dao.admin;

import dao.IDAO;
import models.Order;
import models.OrderDetail;
import models.Product;

import java.util.List;

public interface IDashboadDAO extends IDAO {
    // Lấy số lượng có trong hệ thống
    int total();
}
