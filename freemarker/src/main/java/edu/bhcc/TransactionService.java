package edu.bhcc;

import edu.bhcc.model.Category;
import edu.bhcc.model.Transaction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Transaction Service.
 * @author James Chan
 */
public class TransactionService {
    /**
     * MySQL Connection.
     */
    private static final Connection connection;

    static {
        // load mysql driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                DatabaseConstant.URL,
                DatabaseConstant.USERNAME,
                DatabaseConstant.PASSWORD
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns a list of all transactions from the database.
     * @return a list of Transaction objects representing all transactions in the database.
     * @throws SQLException if there is an error while querying the database.
     */
    public List<Transaction> getAllTransaction() throws SQLException {
        final String sql = "SELECT * FROM `transaction`";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);

        final List<Transaction> transactionList = new ArrayList<>();
        final ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            final Transaction transaction = new Transaction();
            transactionList.add(transaction);

            final long categoryId = resultSet.getLong("category_id");
            transaction.setId(resultSet.getLong("id"));
            transaction.setAmount(resultSet.getInt("amount"));
            transaction.setPayee(resultSet.getString("payee"));
            transaction.setCategoryId(categoryId);
            transaction.setCategory(getCategoryById(categoryId));
        }

        return transactionList;
    }

    /**
     * Returns a Category object from the database by its id.
     * @param categoryId the id of the category to retrieve.
     * @return the Category object representing the category with the specified id.
     * @throws SQLException if there is an error while querying the database.
     */
    public Category getCategoryById(long categoryId) throws SQLException {
        final Category category = new Category();
        category.setId(categoryId);

        final String sql = "SELECT * FROM `category` WHERE `id` = " + categoryId + " LIMIT 1";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);
        final ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            category.setName(resultSet.getString("name"));
            category.setAllocated(resultSet.getInt("allocated"));
        }

        return category;
    }
}
