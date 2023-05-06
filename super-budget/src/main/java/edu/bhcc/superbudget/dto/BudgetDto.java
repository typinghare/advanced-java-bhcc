package edu.bhcc.superbudget.dto;

import java.beans.JavaBean;

/**
 * Budget data transfer object.
 */
@JavaBean
public class BudgetDto {
    /**
     * The id of the category.
     */
    private Long categoryId;

    /**
     * The name of the category.
     */
    private String category;

    /**
     * The money allocated for this category.
     */
    private Double assigned;

    /**
     * The money already spent in this category.
     */
    private Double activity;

    /**
     * The money remained in this category.
     */
    private Double remaining;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getAssigned() {
        return assigned;
    }

    public void setAssigned(Double assigned) {
        this.assigned = assigned;
    }

    public Double getActivity() {
        return activity;
    }

    public void setActivity(Double activity) {
        this.activity = activity;
    }

    public Double getRemaining() {
        return remaining;
    }

    public void setRemaining(Double remaining) {
        this.remaining = remaining;
    }
}
