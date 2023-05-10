package edu.bhcc.superbudget.controller;

import edu.bhcc.superbudget.exception.CategoryNotFoundException;
import edu.bhcc.superbudget.exception.TransactionNotFoundException;
import edu.bhcc.superbudget.model.Category;
import edu.bhcc.superbudget.model.Transaction;
import edu.bhcc.superbudget.service.CategoryService;
import edu.bhcc.superbudget.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Category controller.
 */
@Controller
public class TransactionController {
    /**
     * Category service.
     */
    private final CategoryService categoryService;

    /**
     * Transaction service.
     */
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(CategoryService categoryService, TransactionService transactionService) {
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    /**
     * Adds a transaction.
     * @param payee the payee of this transaction.
     * @param categoryName the name of the category.
     * @param amount the amount of money of this transaction.
     * @param model model
     * @return template name.
     */
    @GetMapping("/add_transaction")
    public String addTransaction(
        @RequestParam("payee") String payee,
        @RequestParam("category") String categoryName,
        @RequestParam("amount") Double amount,
        Model model
    ) {
        try {
            final Category category = categoryService.getCategoryByName(categoryName);
            final Transaction transaction = transactionService.createTransaction(category.getId(), payee, amount);

            model.addAttribute("message", "New transaction added: " + transaction.getPayee() + ".");
        } catch (CategoryNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "index";
    }

    /**
     * Deletes a transaction.
     * @param transactionId the id of the transaction to delete.
     * @param model model
     * @return template name.
     */
    @GetMapping("/delete_transaction")
    public String deleteTransaction(
        @RequestParam("id") Long transactionId,
        Model model
    ) {
        try {
            final Transaction transaction = transactionService.getTransactionById(transactionId);
            transactionService.deleteTransaction(transactionId);

            model.addAttribute("message", "New transaction added: " + transaction.getPayee() + ".");
        } catch (TransactionNotFoundException e) {
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "index";
    }
}
