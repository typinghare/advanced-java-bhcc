package edu.bhcc.superbudget.service;

import edu.bhcc.superbudget.model.Transaction;
import edu.bhcc.superbudget.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Transaction service.
 */
@Service
public class TransactionService {
    /**
     * Transaction repository.
     */
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    /**
     * Creates a transaction.
     * @param categoryId the id of the category to which this transaction belong.
     * @param payee      the payee of this transaction.
     * @param amount     the amount of money of this transaction.
     * @return the category created.
     */
    public Transaction createTransaction(Long categoryId, String payee, Double amount) {
        final Transaction transaction = new Transaction();
        transaction.setCategoryId(categoryId);
        transaction.setPayee(payee);
        transaction.setAmount(amount);

        return transactionRepository.save(transaction);
    }

    /**
     * Retrieves all transactions.
     * @return a list of transactions.
     */
    public List<Transaction> findAllTransaction() {
        return transactionRepository.findAll();
    }

    /**
     * Deletes a transaction.
     * @param transactionId the id of the transaction.
     */
    public void deleteTransaction(Long transactionId) {
        transactionRepository.deleteById(transactionId);
    }
}
