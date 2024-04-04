package dao;

import models.TransactionStatus;

import java.util.List;

public class TransactionStatusDAOImp implements ITransactionStatusDAO {

    public List<TransactionStatus> getListAllTransactionStatus(){
        String sql = "SELECT id, typeStatus FROM transaction_statuses";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, TransactionStatus.class);
    }

    public TransactionStatus getTransactionStatusById(int transactionStatusId){
        String sql = "SELECT id, typeStatus FROM transaction_statuses WHERE id = ?";
        return GeneralDAOImp.executeQueryWithSingleTable(sql, TransactionStatus.class, transactionStatusId).get(0);
    }

    @Override
    public Object getModelById(Object id) {
        return null;
    }
}
