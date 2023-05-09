package edu.bhcc.superbudget.service;

import edu.bhcc.superbudget.dto.BudgetDto;
import edu.bhcc.superbudget.exception.CategoryNotFoundException;
import edu.bhcc.superbudget.model.Category;
import edu.bhcc.superbudget.repository.CategoryRepository;
import edu.bhcc.superbudget.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Category service.
 */
@Service
public class CategoryService {
    /**
     * Category repository.
     */
    private final CategoryRepository categoryRepository;

    /**
     * Transaction repository.
     */
    private final TransactionRepository transactionRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, TransactionRepository transactionRepository) {
        this.categoryRepository = categoryRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Retrieves category by its id.
     * @param categoryId the id of the category to retrieve.
     * @return the category retrieved.
     * @throws CategoryNotFoundException if the category does not exist.
     */
    public Category getCategoryById(Long categoryId) {
        return categoryRepository
            .findById(categoryId)
            .orElseThrow(() -> new CategoryNotFoundException(categoryId));
    }

    /**
     * Retrieves category by its name.
     * @param categoryName the name of the category to retrieve.
     * @return the category found; null if the category does not exist.
     */
    public Category getCategoryByName(String categoryName) {
        return categoryRepository.findFirstByName(categoryName).orElse(null);
    }

    /**
     * Retrieves all categories.
     * @return a list of categories.
     */
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    /**
     * Creates a category.
     * @param categoryName the name of the category.
     * @param allocated    The total amount of money of transactions allocated for this category.
     * @return the category created.
     */
    public Category createCategory(String categoryName, Double allocated) {
        final Category category = new Category();
        category.setName(categoryName);
        category.setAllocated(allocated);

        return categoryRepository.save(category);
    }

    /**
     * Deletes a category.
     * @param categoryId the id of the category to delete.
     */
    public void deleteCategoryById(Long categoryId) {
        categoryRepository.deleteById(categoryId);

        // Delete all transactions where category is this one.
        transactionRepository.deleteByCategoryId(categoryId);
    }

    /**
     * Creates a category.
     * @param categoryId   the id of the category to update.
     * @param categoryName the name of the category.
     * @param allocated    The total amount of money of transactions allocated for this category.
     * @return the category created.
     * @throws CategoryNotFoundException if the category does not exist.
     */
    public Category updateCategory(Long categoryId, String categoryName, Double allocated) {
        final Category category = getCategoryById(categoryId);

        if (categoryName != null) category.setName(categoryName);
        if (allocated != null) category.setAllocated(allocated);
        return categoryRepository.save(category);
    }

    /**
     * Returns all budget DTOs.
     * @return a list of budget DTO.
     */
    public List<BudgetDto> getAllBudgetDto() {
        final List<BudgetDto> budgetDtoList = new ArrayList<>();
        for (final Category category : getAllCategory()) {
            final BudgetDto budgetDto = new BudgetDto();

            // Gets` activity` (the amount of money spent) by aggregation.
            final Double activity = transactionRepository
                .getSumByCategoryId(category.getId())
                .orElse(0.0);

            budgetDto.setCategoryId(category.getId());
            budgetDto.setCategory(category.getName());
            budgetDto.setActivity(activity);
            budgetDto.setAssigned(category.getAllocated());
            budgetDto.setRemaining(category.getAllocated() - activity);

            budgetDtoList.add(budgetDto);
        }

        return budgetDtoList;
    }
}
