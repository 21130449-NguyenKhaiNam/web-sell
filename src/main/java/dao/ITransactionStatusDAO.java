package dao;

import models.TransactionStatus;

import java.util.List;

public interface ITransactionStatusDAO {
    List<TransactionStatus> getListAllTransactionStatus();
    TransactionStatus getTransactionStatusById(int transactionStatusId);
}
