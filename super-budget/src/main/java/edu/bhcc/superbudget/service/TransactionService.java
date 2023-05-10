package edu.bhcc.superbudget.service;

import edu.bhcc.superbudget.dto.TransactionDto;
import edu.bhcc.superbudget.exception.TransactionNotFoundException;
import edu.bhcc.superbudget.model.Category;
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

    /**
     * Category service.
     */
    private final CategoryService categoryService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, CategoryService categoryService) {
        this.transactionRepository = transactionRepository;
        this.categoryService = categoryService;
    }

    /**
     * Converts a transaction model to a transaction data transfer object.
     * @param transaction the transaction model to convert.
     * @return a transaction data transfer object.
     */
    public TransactionDto toTransactionDto(Transaction transaction) {
        final Long categoryId = transaction.getCategory().getId();
        final Category category = categoryService.getCategoryById(categoryId);

        final TransactionDto transactionDto = new TransactionDto();
        transactionDto.setId(transaction.getId());
        transactionDto.setPayee(transaction.getPayee());
        transactionDto.setCategory(category.getName());
        transactionDto.setAmount(Math.round(transaction.getAmount() * 100.) / 100.);

        return transactionDto;
    }

    /**
     * Creates a transaction.
     * @param categoryId the id of the category to which this transaction belong.
     * @param payee      the payee of this transaction.
     * @param amount     the amount of money of this transaction.
     * @return the category created.
     */
    public Transaction createTransaction(Long categoryId, String payee, Double amount) {
        final Category category = categoryService.getCategoryById(categoryId);
        final Transaction transaction = new Transaction();
        transaction.setCategory(category);
        transaction.setPayee(payee);
        transaction.setAmount(amount);

        return transactionRepository.save(transaction);
    }

    /**
     * Retrieves a transaction by its id.
     * @param transactionId the id of the transaction to retrieve.
     * @return the transaction retrieved.
     */
    public Transaction getTransactionById(Long transactionId) {
        return transactionRepository
            .findById(transactionId)
            .orElseThrow(() -> new TransactionNotFoundException(transactionId));
    }

    /**
     * Retrieves all transactions.
     * @return a list of transactions.
     */
    public List<Transaction> getAllTransaction() {
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
