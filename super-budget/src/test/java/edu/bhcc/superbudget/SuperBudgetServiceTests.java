package edu.bhcc.superbudget;

import edu.bhcc.superbudget.dto.BudgetDto;
import edu.bhcc.superbudget.model.Category;
import edu.bhcc.superbudget.model.Transaction;
import edu.bhcc.superbudget.repository.TransactionRepository;
import edu.bhcc.superbudget.service.CategoryService;
import edu.bhcc.superbudget.service.TransactionService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test suite for service classes. The database should be empty before testing.
 */
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SuperBudgetServiceTests {
    private final CategoryService categoryService;

    private final TransactionService transactionService;

    private final TransactionRepository transactionRepository;

    @Autowired
    public SuperBudgetServiceTests(
        CategoryService categoryService,
        TransactionService transactionService,
        TransactionRepository transactionRepository
    ) {
        this.categoryService = categoryService;
        this.transactionService = transactionService;
        this.transactionRepository = transactionRepository;
    }

    @Test
    @Order(1)
    void createCategoryTest() {
        // Create three categories.
        final Category category1 = categoryService.createCategory("Category1", 50.);
        final Category category2 = categoryService.createCategory("Category2", 85.25);
        final Category category3 = categoryService.createCategory("Category3", 100.);
        assertThat(category1).isNotNull();
        assertThat(category2).isNotNull();
        assertThat(category3).isNotNull();
    }

    @Test
    @Order(2)
    void findAllCategoryTest() {
        // Find all categories.
        final List<Category> categoryList = categoryService.getAllCategory();
        assertThat(categoryList.size()).isEqualTo(3);
        assertThat(categoryList.get(0).getClass()).isEqualTo(Category.class);
    }

    @Test
    @Order(3)
    void deleteCategoryTest() {
        // Delete a category.
        categoryService.deleteCategoryById(2L);

        final List<Category> categoryList = categoryService.getAllCategory();
        assertThat(categoryList.size()).isEqualTo(2);
    }

    @Test
    @Order(4)
    void updateCategoryTest() {
        // Update a category.
        final Category category = categoryService.updateCategory(1L, null, 75.);

        assertThat(category.getName()).isEqualTo("Category1");
        assertThat(category.getAllocated()).isEqualTo(75.);
    }

    @Test
    @Order(5)
    void createTransactionTest() {
        // Create three transactions.
        final Transaction transaction1 = transactionService.createTransaction(1L, "Target", 28.5);
        final Transaction transaction2 = transactionService.createTransaction(1L, "Walmart", 31.75);
        final Transaction transaction3 = transactionService.createTransaction(3L, "GameStop", 70.);
        final Transaction transaction4 = transactionService.createTransaction(3L, "Star", 73.1);

        assertThat(transaction1).isNotNull();
        assertThat(transaction2).isNotNull();
        assertThat(transaction3).isNotNull();
        assertThat(transaction4).isNotNull();
    }

    @Test
    @Order(6)
    void findTransactionTest() {
        // Find all transactions;
        final List<Transaction> transactionList = transactionRepository.findAll();

        assertThat(transactionList.size()).isEqualTo(4);
        assertThat(transactionList.get(0).getClass()).isEqualTo(Transaction.class);
    }

    @Test
    @Order(7)
    void deleteTransactionTest() {
        // Delete a transaction.
        transactionService.deleteTransaction(4L);

        final List<Transaction> transactionList = transactionRepository.findAll();
        assertThat(transactionList.size()).isEqualTo(3);
    }

    @Test
    @Order(8)
    void getBudgetDtoTest() {
        // Get budget DTOs.
        final List<BudgetDto> budgetDtoList = categoryService.getAllBudgetDto();

        // 1 => 28.5 + 31.75 = 60.25
        // 3 => 70.
        final Double category1Activity = budgetDtoList.stream()
            .filter(budgetDto -> budgetDto.getCategoryId() == 1L)
            .findFirst()
            .map(BudgetDto::getActivity)
            .orElse(0.);
        final Double category2Activity = budgetDtoList.stream()
            .filter(budgetDto -> budgetDto.getCategoryId() == 3L)
            .findFirst()
            .map(BudgetDto::getActivity)
            .orElse(0.);

        assertThat(category1Activity).isEqualTo(60.25);
        assertThat(category2Activity).isEqualTo(70.);
    }
}
