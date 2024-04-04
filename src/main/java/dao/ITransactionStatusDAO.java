package dao;

import models.TransactionStatus;

import java.util.List;

public interface ITransactionStatusDAO {
    //    Lấy ra danh sách chi tiết của tất cả các trạng thái giao dịch
    List<TransactionStatus> getListAllTransactionStatus();

    //    Lấy ra chi tiết trạng thái giao dịch dựa vào id trạng thái giao dịch
    TransactionStatus getTransactionStatusById(int transactionStatusId);
}
