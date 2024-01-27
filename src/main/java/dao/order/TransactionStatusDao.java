package dao.order;

import dao.GeneralDao;
import models.TransactionStatus;

import java.util.List;

public class TransactionStatusDao {

    public List<TransactionStatus> getListAllTransactionStatus(){
        String sql = "SELECT id, typeStatus FROM transaction_statuses";
        return GeneralDao.executeQueryWithSingleTable(sql, TransactionStatus.class);
    }

    public TransactionStatus getTransactionStatusById(int transactionStatusId){
        String sql = "SELECT id, typeStatus FROM transaction_statuses WHERE id = ?";
        return GeneralDao.executeQueryWithSingleTable(sql, TransactionStatus.class, transactionStatusId).get(0);
    }
}
