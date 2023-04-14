package edu.bhcc.model;

import java.beans.JavaBean;

/**
 * Transaction Model.
 */
@JavaBean
public class Transaction {
    /**
     * Primary key.
     */
    private long id;

    /**
     * The amount of transaction (in cent).
     */
    private int amount;

    /**
     * The name of the payee.
     */
    private String payee;

    /**
     * The foreign id of the category.
     */
    private long categoryId;

    /**
     * The category joined.
     */
    private Category category;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
