package edu.bhcc.superbudget.controller;

import edu.bhcc.superbudget.exception.CategoryNotFoundException;
import edu.bhcc.superbudget.model.Category;
import edu.bhcc.superbudget.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Category controller.
 */
@Controller
public class CategoryController {
    /**
     * Category service.
     */
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    /**
     * Adds a category.
     * @param categoryName the name of the category to add
     * @param allocated    the amount of money to allocate for the category.
     * @return template name.
     */
    @GetMapping("add_budget_category")
    public String addOrUpdateCategory(
        @RequestParam("category") String categoryName,
        @RequestParam("allocated") Double allocated,
        Model model
    ) {
        try {
            final Category category = categoryService.getCategoryByName(categoryName);

            // Category already exists, update it.
            categoryService.updateCategory(category.getId(), categoryName, allocated);
            model.addAttribute("message", "Budget category updated: " + categoryName);
        } catch (CategoryNotFoundException e) {
            // Category does not exist, create it.
            categoryService.createCategory(categoryName, allocated);
            model.addAttribute("message", "New budget category added: " + categoryName + ".");
        }

        return "index";
    }

    /**
     * Deletes a category.
     * @param categoryId the id of the category to delete.
     * @return template name.
     */
    @GetMapping("delete_category")
    public String deleteCategory(@RequestParam("id") Long categoryId, Model model) {
        try {
            final Category category = categoryService.getCategoryById(categoryId);
            categoryService.deleteCategoryById(categoryId);

            model.addAttribute("message", "Category deleted: " + category.getName() + ".");
        } catch (CategoryNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "index";
    }
}
