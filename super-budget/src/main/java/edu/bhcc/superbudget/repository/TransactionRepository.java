package edu.bhcc.superbudget.repository;

import edu.bhcc.superbudget.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Transaction repository.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    /**
     * Deletes all transactions where id is equal to the given one.
     * @param categoryId the category id.
     */
    void deleteByCategoryId(Long categoryId);

    /**
     * Returns the summation (aggregation) of the `amount` of records where category id is equal to the given
     * one in the table `transaction`.
     * @param categoryId the category id of transactions to aggregate.
     * @return an optional summation; the summation will not be presented if no transaction found.
     * @deprecated Now using the `OneToMany`.
     */
    @Deprecated()
    @Query(value = "SELECT SUM(amount) FROM transaction WHERE category_id = :categoryId", nativeQuery = true)
    Optional<Double> getSumByCategoryId(@Param("categoryId") Long categoryId);
}
