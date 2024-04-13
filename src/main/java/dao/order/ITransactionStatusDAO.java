package dao.order;

import dao.IDAO;
import models.TransactionStatus;

import java.util.List;

public interface ITransactionStatusDAO extends IDAO {

    @Override
    default <T> T selectById(int id) {
        return null;
    }

    @Override
    default <T> int insert(T o) {
        return 0;
    }

    @Override
    default <T> int insertAll(List<T> list) {
        return 0;
    }

    @Override
    default int update(Object o) {
        return 0;
    }

    //    Lấy ra danh sách chi tiết của tất cả các trạng thái giao dịch
    List<TransactionStatus> getListAllTransactionStatus();

    //    Lấy ra chi tiết trạng thái giao dịch dựa vào id trạng thái giao dịch
    TransactionStatus getTransactionStatusById(int transactionStatusId);
}
