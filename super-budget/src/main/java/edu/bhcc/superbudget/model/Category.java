package edu.bhcc.superbudget.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;

import java.beans.JavaBean;
import java.util.List;

/**
 * Category Model.
 */
@Entity
@JavaBean
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Comment("The primary key of this table")
    private Long id;

    @Column(name = "name", nullable = false, length = 32)
    @Comment("The name of the category.")
    private String name;

    @Column(name = "allocated", nullable = false)
    @Comment("The total amount of money of transactions allocated for this category.")
    private Double allocated;

    @OneToMany(mappedBy = "category", orphanRemoval = true)
    private List<Transaction> transactionList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getAllocated() {
        return allocated;
    }

    public void setAllocated(Double allocated) {
        this.allocated = allocated;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
