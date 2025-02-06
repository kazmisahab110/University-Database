import java.sql.*;

public class Main2 {
    public static void main(String[] args) {
        try {
            // Establishing connection
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/myuni",
                    "root",
                    "password"
            );

            String query = "SELECT id, grade " +
                   "FROM takes " +
                   "ORDER BY CASE " +
                   "    WHEN grade = 'A+' THEN 1 " +
                   "    WHEN grade = 'A' THEN 2 " +
                   "    WHEN grade = 'A-' THEN 3 " +
                   "    WHEN grade = 'B+' THEN 4 " +
                   "    WHEN grade = 'B' THEN 5 " +
                   "    WHEN grade = 'B-' THEN 6 " +
                   "    WHEN grade = 'C' THEN 7 " +
                   "    WHEN grade = 'D' THEN 8 " +
                   "    WHEN grade = 'F' THEN 9 " +
                   "    ELSE 999 " +
                   "END " +
                   "LIMIT 1";

    try (Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query)) {

        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String grade = resultSet.getString("grade");
            System.out.println("Student ID: " + id + ", Grade: " + grade);
        } else {
            System.out.println("No students found.");
        }
    }
            // Closing the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
