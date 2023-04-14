package edu.bhcc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Load Budget from CSV File.
 */
public class BudgetLoader {
    private final Connection connection;

    /**
     * Main Method.
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
        new BudgetLoader("budget.csv");
    }

    /**
     * Constructor.
     */
    public BudgetLoader(String fileName) throws ClassNotFoundException, SQLException, IOException {
        //  Load the MySQL JDBC Driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        //  Create connect string
        String connectUrl = "jdbc:mysql://localhost/ynab";

        //  Create JDBC Connection
        this.connection = DriverManager.getConnection(connectUrl, "user", "password");

        // Load the Budget.
        loadBudget(fileName);

        // Close Connection.
        this.connection.close();
    }

    /**
     * Load Budget from CSV File.
     */
    private void loadBudget(String fileName) throws IOException, SQLException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        while (line != null) {
            String[] values = line.split(",");
            System.out.println("Record: " + values[0] + " " + values[1]);
            this.addRecord(values);
            line = br.readLine();
        }
    }

    /**
     * Add Budget Category Record.
     */
    private void addRecord(String[] values) throws SQLException {
        System.out.println("Adding budget category:  " + values[0]);
        PreparedStatement preparedStatement = this.connection.prepareStatement("insert into CATEGORY " +
            "(CATEGORY_NAME, ALLOCATED) values (?, ?);");
        preparedStatement.setString(1, values[0]);
        preparedStatement.setFloat(2, Float.parseFloat(values[1]));
        preparedStatement.executeUpdate();
    }
}