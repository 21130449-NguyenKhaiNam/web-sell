package dao.order;

import dao.IDAO;
import models.TransactionStatus;

import java.util.List;

public interface ITransactionStatusDAO extends IDAO {
    //    Lấy ra danh sách chi tiết của tất cả các trạng thái giao dịch
    List<TransactionStatus> getListAllTransactionStatus();
}
