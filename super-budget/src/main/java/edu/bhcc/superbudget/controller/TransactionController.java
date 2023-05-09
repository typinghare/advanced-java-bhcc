package edu.bhcc.superbudget.controller;

import edu.bhcc.superbudget.model.Transaction;
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
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/add_transaction")
    public String addTransaction(
        @RequestParam("payee") String payee,
        @RequestParam("category") String categoryName,
        @RequestParam("amount") Double amount,
        Model model
    ) {
        final Transaction transaction = transactionService.createTransaction(1L, payee, amount);

        model.addAttribute("message", "New transaction added: " + transaction.getPayee() + ".");

        return "index";
    }

    @GetMapping("/delete_transaction")
    public String deleteTransaction(
        @RequestParam("id") Long transactionId,
        Model model
    ) {
        final Transaction transaction = transactionService.getTransactionById(transactionId);
        transactionService.deleteTransaction(transactionId);

        model.addAttribute("message", "New transaction added: " + transaction.getPayee() + ".");

        return "index";
    }
}
