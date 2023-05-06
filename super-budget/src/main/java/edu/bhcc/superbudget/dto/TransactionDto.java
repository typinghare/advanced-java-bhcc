package edu.bhcc.superbudget.dto;

import java.beans.JavaBean;

/**
 * Category data transfer object.
 */
@JavaBean
public class TransactionDto {
    /**
     * The payee of this transaction.
     */
    private String payee;

    /**
     * The name of the category.
     */
    private String category;

    /**
     * The amount of money paid in this transaction.
     */
    private Double amount;

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
