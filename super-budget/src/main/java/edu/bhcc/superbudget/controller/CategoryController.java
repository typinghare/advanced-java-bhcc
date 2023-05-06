package edu.bhcc.superbudget.controller;

import edu.bhcc.superbudget.model.Category;
import edu.bhcc.superbudget.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("add_budget_category")
    public String addOrUpdateCategory(
        @RequestParam("category") String categoryName,
        @RequestParam("allocated") Double allocated,
        Model model
    ) {
        System.out.println(categoryName);
        System.out.println(allocated);

        final Category category = categoryService.findCategoryByName(categoryName);
        if (category != null) {
            // Category already exists, update it.
            categoryService.updateCategory(category.getId(), categoryName, allocated);
            model.addAttribute("message", "Budget category updated: " + categoryName);
        } else {
            // Category does not exist, create it.
            categoryService.createCategory(categoryName, allocated);
            model.addAttribute("message", "Budget category added: " + categoryName);
        }

        return "index";
    }

    @GetMapping("delete_category")
    public String deleteCategory(@Param("id") Long categoryId, Model model) {

        return "index";
    }
}
