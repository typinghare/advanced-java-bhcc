package edu.bhcc.superbudget.controller;

import edu.bhcc.superbudget.dto.TransactionDto;
import edu.bhcc.superbudget.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
        @Param("payee") String payee,
        @Param("category") String categoryName,
        @Param("amount") Double amount,
        Model model
    ) {
        model.addAttribute("transactionDtoList", List.of(new TransactionDto()));

        return "index";
    }
}
