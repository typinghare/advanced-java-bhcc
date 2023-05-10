package edu.bhcc.superbudget.exception;

/**
 * Category not found exception
 */
public class CategoryNotFoundException extends RuntimeException {
    /**
     * Creates a category not found exception.
     * @param categoryId the id of the category.
     */
    public CategoryNotFoundException(Long categoryId) {
        super("Category does not exist. Id = " + categoryId + ".");
    }

    /**
     * Creates a category not found exception.
     * @param categoryName the name of the category.
     */
    public CategoryNotFoundException(String categoryName) {
        super("Category does not exist. Name = " + categoryName + ".");
    }
}
