package edu.bhcc.superbudget.exception;

/**
 * Transaction not found exception
 */
public class TransactionNotFoundException extends RuntimeException {
    /**
     * Creates a transaction not found exception.
     * @param transactionId the id of the transaction.
     */
    public TransactionNotFoundException(Long transactionId) {
        super("The transaction does not exist. Id = " + transactionId + ".");
    }
}
