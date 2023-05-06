package edu.bhcc.superbudget.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

/**
 * Transaction Model.
 */
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Comment("The primary key of this table.")
    private Long id;

    @Column(name = "category_id", nullable = false)
    @Comment("The id of the category of which this transaction belong.")
    private Long categoryId;

    @Column(name = "payee", nullable = false)
    @Comment("Payee of this transaction.")
    private String payee;

    @Column(name = "amount", nullable = false)
    @Comment("The amount of money of this transaction.")
    private Double amount;

//    @ManyToOne()
//    private Category category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
