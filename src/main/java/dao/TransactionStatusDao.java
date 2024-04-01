package dao;

import models.TransactionStatus;

import java.util.List;

public class TransactionStatusDao {

    public List<TransactionStatus> getListAllTransactionStatus(){
        String sql = "SELECT id, typeStatus FROM transaction_statuses";
        return GeneralDAO.executeQueryWithSingleTable(sql, TransactionStatus.class);
    }

    public TransactionStatus getTransactionStatusById(int transactionStatusId){
        String sql = "SELECT id, typeStatus FROM transaction_statuses WHERE id = ?";
        return GeneralDAO.executeQueryWithSingleTable(sql, TransactionStatus.class, transactionStatusId).get(0);
    }
}
