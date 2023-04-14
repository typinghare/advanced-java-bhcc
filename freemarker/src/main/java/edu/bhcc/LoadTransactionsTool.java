package edu.bhcc;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.sql.*;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * Load transactions Tool.
 */
public class LoadTransactionsTool {
    public static void main(String[] args) throws Exception {
        final Scanner scanner = new Scanner(System.in);

        System.out.print("Please input the CSV file path: ");
        final String filepath = scanner.nextLine();
        // src/main/resources/transactions.csv

        // load mysql driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        final Connection connection
            = DriverManager.getConnection(DatabaseConstant.URL, DatabaseConstant.USERNAME, DatabaseConstant.PASSWORD);

        final String absolutePath = new File(filepath).getAbsolutePath();
        final Reader reader = new FileReader(absolutePath);
        final Stream<CSVRecord> recordStream = CSVFormat.EXCEL.parse(reader).stream();

        recordStream.skip(1).forEach(record -> {
            final String payee = record.get(0);
            final double amount = Double.parseDouble(record.get(1));
            final String categoryName = record.get(2);

            try {
                // retrieve category id
                long categoryId;
                final String sql1 = "SELECT `id` FROM `category` WHERE `name` = \"" + categoryName + "\" LIMIT 1";
                System.out.println(sql1);
                final PreparedStatement preparedStatement1
                    = connection.prepareStatement(sql1);
                final ResultSet resultSet = preparedStatement1.executeQuery();
                if (resultSet.next()) {
                    categoryId = resultSet.getLong("id");
                } else {
                    throw new Exception("Category Not found.");
                }

                final String sql2 = "INSERT INTO `transaction` (`amount`,`payee`,`category_id`) VALUES (?,?,?)";
                System.out.println(sql2);

                final PreparedStatement preparedStatement
                    = connection.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setLong(1, (long) amount * 100);
                preparedStatement.setString(2, payee);
                preparedStatement.setLong(3, categoryId);

                preparedStatement.executeUpdate();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
