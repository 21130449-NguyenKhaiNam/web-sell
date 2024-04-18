package dao.order;

import dao.general.GeneralDAO;
import models.TransactionStatus;

import java.util.List;

public class TransactionStatusDAOImp implements ITransactionStatusDAO {
    @Override
    public TransactionStatus selectById(Object id) {
        if(id instanceof Integer) {
            String sql = "SELECT id, typeStatus FROM transaction_statuses WHERE id = ?";
            return GeneralDAO.executeQueryWithSingleTable(sql, TransactionStatus.class, id).get(0);
        } else {
            throw new UnsupportedOperationException("TransactionStatusDAOImp >> Phương thức thêm không hỗ trợ tham số kiểu khác");
        }
    }

    @Override
    public List<TransactionStatus> getListAllTransactionStatus(){
        String sql = "SELECT id, typeStatus FROM transaction_statuses";
        return GeneralDAO.executeQueryWithSingleTable(sql, TransactionStatus.class);
    }

}
