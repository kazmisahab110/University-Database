import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Establishing connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/myuni",
                    "root",
                    "password"
            );

            // Creating and executing the SQL query
            String sqlQuery = "SELECT id, Name, Salary, (Salary * 0.1) AS tax_amount FROM instructor GROUP BY id";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            // Iterating over the result set
            while (resultSet.next()) {
                // Retrieving values from the result set
                int id = resultSet.getInt("id");
                String name = resultSet.getString("Name");
                int salary = resultSet.getInt("Salary");
                double taxAmount = resultSet.getDouble("tax_amount");

                // Printing the results
                System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary + ", Tax Amount: " + taxAmount);
            }

            // Closing the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
