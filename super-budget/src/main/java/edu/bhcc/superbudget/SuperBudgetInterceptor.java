package edu.bhcc.superbudget;

import edu.bhcc.superbudget.dto.BudgetDto;
import edu.bhcc.superbudget.dto.TransactionDto;
import edu.bhcc.superbudget.model.Transaction;
import edu.bhcc.superbudget.service.CategoryService;
import edu.bhcc.superbudget.service.TransactionService;
import io.micrometer.common.lang.NonNullApi;
import io.micrometer.common.lang.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * A custom handler interceptor.
 */
@NonNullApi
@Component
public class SuperBudgetInterceptor implements HandlerInterceptor {
    /**
     * Category service.
     */
    private final CategoryService categoryService;

    /**
     * Transaction service.
     */
    private final TransactionService transactionService;

    @Autowired
    public SuperBudgetInterceptor(CategoryService categoryService, TransactionService transactionService) {
        this.categoryService = categoryService;
        this.transactionService = transactionService;
    }

    /**
     * This method is called after the controller method is executed, but before the response is sent back to
     * the client. Here, we put to the model more attributes, including a list of budget DTO and a list of
     * transaction DTO.
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) {
        if (modelAndView != null) {
            final ModelMap modelMap = modelAndView.getModelMap();

            final List<BudgetDto> budgetDtoList = categoryService.getAllBudgetDto();
            modelMap.put("budgetDtoList", budgetDtoList);

            final List<Transaction> transactionList = transactionService.getAllTransaction();
            final List<TransactionDto> transactionDtoList =
                transactionList.stream().map(transactionService::toTransactionDto).toList();
            modelMap.put("transactionDtoList", transactionDtoList);
        }
    }
}
