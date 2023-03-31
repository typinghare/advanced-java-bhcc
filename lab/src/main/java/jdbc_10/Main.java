package jdbc_10;

import constant.DatabaseConstant;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) throws Exception {
        final Connection connection = DriverManager.getConnection(
            DatabaseConstant.URL + DatabaseConstant.DATABASE_NAME,
            DatabaseConstant.USERNAME,
            DatabaseConstant.PASSWORD
        );

        final String sql = "SELECT `id`, `name`, `height`, `mass`, `gender` FROM wars_character;";
        final PreparedStatement preparedStatement = connection.prepareStatement(sql);

        final ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            final long id = resultSet.getLong("id");
            final String name = resultSet.getString("name");
            final double height = resultSet.getDouble("height");
            final double mass = resultSet.getDouble("mass");
            final String gender = resultSet.getString("gender");

            System.out.println("id: " + id + "\n" + "name: " + name + "\n" + "height: " + height + "\n" + "mass: " + mass + "\n" + "gender: " + gender);
            System.out.println();
        }
    }
}