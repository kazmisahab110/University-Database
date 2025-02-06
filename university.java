//database connector
import java.sql.*;
public class university
{
    public static void main(String[] args)
    {
        try
        {
            Connection connection = DriverManager.getConnection(
            "jdbc:mysql://127.0.0.1:3306/university",
            "root",
            "password"
            );
            
            
            
            Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery("SELECT * FROM INSTRUCTOR");
            System.out.println("Connected to the database!");
            
            // Don't forget to close the connection when done
            connection.close();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}