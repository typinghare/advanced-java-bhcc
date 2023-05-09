package edu.bhcc.superbudget.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.beans.JavaBean;

/**
 * Transaction Model.
 */
@Entity
@JavaBean
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Comment("The primary key of this table.")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @Comment("The category of which this transaction belong.")
    private Category category;

    @Column(name = "payee", nullable = false)
    @Comment("Payee of this transaction.")
    private String payee;

    @Column(name = "amount", nullable = false)
    @Comment("The amount of money of this transaction.")
    private Double amount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
