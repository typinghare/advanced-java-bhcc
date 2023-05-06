package edu.bhcc.superbudget.exception;

/**
 * Category not found exception
 */
public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(Long categoryId) {
        super("The id of the category is: " + categoryId);
    }

    public CategoryNotFoundException(String categoryName) {
        super("The name of the category is: " + categoryName);
    }
}
