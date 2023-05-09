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
        super("The id of the category is: " + categoryId);
    }

    /**
     * Creates a category not found exception.
     * @param categoryName the name of the category.
     */
    @Deprecated
    public CategoryNotFoundException(String categoryName) {
        super("The name of the category is: " + categoryName);
    }
}
