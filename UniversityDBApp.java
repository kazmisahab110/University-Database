import java.sql.*;
import java.util.Scanner;

public class UniversityDBApp {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/myuni";
    private static final String USER = "root";
    private static final String PASSWORD = "password";

    public static void main(String[] args) {
        try {
            // Establishing connection
            Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);

            // Creating a Scanner object to read user input
            Scanner scanner = new Scanner(System.in);

             // Main menu
            while (true) {
                System.out.println("University Database Menu:");
                System.out.println("1. View Courses");
                System.out.println("2. View Students");
                System.out.println("3. View Departments");
                System.out.println("4. Add Student");
                System.out.println("5. Delete Student");
                System.out.println("6. Add Instructor");
                System.out.println("7. Remove Instructor");
                System.out.println("8. Print Salaries and Tax Amounts of Instructors");
                System.out.println("9. Change Student's Department");
                System.out.println("10. Add Course");
                System.out.println("11. Remove Course");
                System.out.println("12. Display Highest Grade Among Students");
                System.out.println("13. Add Classroom");
                System.out.println("14. Display Capacity of a Classroom");
                System.out.println("15. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                

                switch (choice) {
                    case 1:
                        viewCourses(connection);
                        break;
                    case 2:
                        viewStudents(connection);
                        break;
                    case 3:
                        viewDepartments(connection);
                        break;
                    case 4:
                        addStudent(connection, scanner);
                        break;
                    case 5:
                        deleteStudent(connection, scanner);
                        break;
                    case 6:
                        addInstructor(connection, scanner);
                        break;
                    case 7:
                        removeInstructor(connection, scanner);
                        break;
                    case 8:
                        printSalariesAndTax(connection);
                        break;
                    case 9:
                        changeStudentDepartment(connection, scanner);
                        break;
                    case 10:
                        addCourse(connection, scanner);
                        break;
                    case 11:
                        removeCourse(connection, scanner);
                        break;
                    case 12:
                        displayHighestGrade(connection);
                        break;
                    case 13:
                        addClassroom(connection, scanner);
                        break;
                    case 14:
                        displayClassroomCapacity(connection);
                        break;
                    case 15:
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

// Function to view courses
private static void viewCourses(Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM Course");

    ResultSetMetaData metaData = resultSet.getMetaData();
    int columnsNumber = metaData.getColumnCount();

    System.out.println("Courses:");
    System.out.println("-------------------------------------------------------------");

    // Print column headers
    for (int i = 1; i <= columnsNumber; i++) {
        System.out.printf("%-15s", metaData.getColumnName(i));
    }
    System.out.println("\n-------------------------------------------------------------");

    // Print rows
    while (resultSet.next()) {
        for (int i = 1; i <= columnsNumber; i++) {
            System.out.printf("%-15s", resultSet.getString(i));
        }
        System.out.println();
    }
}

// Function to view students
private static void viewStudents(Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM student");

    ResultSetMetaData metaData = resultSet.getMetaData();
    int columnsNumber = metaData.getColumnCount();

    System.out.println("Students:");
    System.out.println("-------------------------------------------------------------");

    // Print column headers
    for (int i = 1; i <= columnsNumber; i++) {
        System.out.printf("%-15s", metaData.getColumnName(i));
    }
    System.out.println("\n-------------------------------------------------------------");

    // Print rows
    while (resultSet.next()) {
        for (int i = 1; i <= columnsNumber; i++) {
            System.out.printf("%-15s", resultSet.getString(i));
        }
        System.out.println();
    }
}

    // Function to view departments
private static void viewDepartments(Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM department");

    ResultSetMetaData metaData = resultSet.getMetaData();
    int columnsNumber = metaData.getColumnCount();

    System.out.println("Departments:");
    System.out.println("-------------------------------------------------------------");

    // Print column headers
    for (int i = 1; i <= columnsNumber; i++) {
        System.out.printf("%-15s", metaData.getColumnName(i));
    }
    System.out.println("\n-------------------------------------------------------------");

    // Print rows
    while (resultSet.next()) {
        for (int i = 1; i <= columnsNumber; i++) {
            System.out.printf("%-15s", resultSet.getString(i));
        }
        System.out.println();
    }
}
    
    private static void addStudent(Connection connection, Scanner scanner) throws SQLException {
    System.out.println("Enter student details:");
    System.out.print("ID: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    System.out.print("Name: ");
    String name = scanner.nextLine();
    System.out.print("Department Name: ");
    String deptName = scanner.nextLine();
    System.out.print("Total Credits: ");
    int totCred = scanner.nextInt();

    // Check if the department exists
    if (checkDepartmentExists(connection, deptName)) {
        String insertStudentQuery = "INSERT INTO student (id, name, dept_name, tot_Cred) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertStudentQuery);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, deptName);
        preparedStatement.setInt(4, totCred);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Failed to add student.");
        }
    } else {
        System.out.println("Department does not exist.");
    }
}
    
    // Function to delete a student
    private static void deleteStudent(Connection connection, Scanner scanner) throws SQLException {
    System.out.println("Enter ID of student to delete:");
    int id = scanner.nextInt();
    String deleteStudentQuery = "DELETE FROM student WHERE id = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(deleteStudentQuery);
    preparedStatement.setInt(1, id);
    int rowsAffected = preparedStatement.executeUpdate();
    if (rowsAffected > 0) {
        System.out.println("Student deleted successfully.");
    } else {
        System.out.println("Failed to delete student. Student not found.");
    }
    }
    
    // Function to add an instructor
private static void addInstructor(Connection connection, Scanner scanner) throws SQLException {
    System.out.println("Enter instructor details:");
    System.out.print("ID: ");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    System.out.print("Name: ");
    String name = scanner.nextLine();
    System.out.print("Department Name: ");
    String deptName = scanner.nextLine();
    System.out.print("Salary: ");
    int salary = scanner.nextInt();

    // Check if the department exists
    if (checkDepartmentExists(connection, deptName)) {
        String insertInstructorQuery = "INSERT INTO instructor (ID, Name, Dept_Name, Salary) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertInstructorQuery);
        preparedStatement.setInt(1, id);
        preparedStatement.setString(2, name);
        preparedStatement.setString(3, deptName);
        preparedStatement.setInt(4, salary);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Instructor added successfully.");
        } else {
            System.out.println("Failed to add instructor.");
        }
    } else {
        System.out.println("Department does not exist.");
    }
}

// Function to check if a department exists
private static boolean checkDepartmentExists(Connection connection, String deptName) throws SQLException {
    String query = "SELECT COUNT(*) FROM department WHERE dept_name = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(query);
    preparedStatement.setString(1, deptName);
    ResultSet resultSet = preparedStatement.executeQuery();
    resultSet.next();
    int count = resultSet.getInt(1);
    return count > 0;
}

// Function to remove an instructor
private static void removeInstructor(Connection connection, Scanner scanner) throws SQLException {
    System.out.println("Enter ID of instructor to remove:");
    int id = scanner.nextInt();
    String deleteInstructorQuery = "DELETE FROM instructor WHERE ID = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(deleteInstructorQuery);
    preparedStatement.setInt(1, id);
    int rowsAffected = preparedStatement.executeUpdate();
    if (rowsAffected > 0) {
        System.out.println("Instructor removed successfully.");
    } else {
        System.out.println("Failed to remove instructor. Instructor not found.");
    }
}

// Function to print salaries and tax amounts of instructors
private static void printSalariesAndTax(Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT ID, Name, Salary, (Salary * 0.1) AS Tax_Amount FROM instructor");

    ResultSetMetaData metaData = resultSet.getMetaData();
    int columnsNumber = metaData.getColumnCount();

    System.out.println("Salaries and Tax Amounts of Instructors:");
    System.out.println("-------------------------------------------------------------");

    // Print column headers
    for (int i = 1; i <= columnsNumber; i++) {
        System.out.printf("%-15s", metaData.getColumnName(i));
    }
    System.out.println("\n-------------------------------------------------------------");

    // Print rows
    while (resultSet.next()) {
        for (int i = 1; i <= columnsNumber; i++) {
            System.out.printf("%-15s", resultSet.getString(i));
        }
        System.out.println();
    }
}


// Function to change a student's department
private static void changeStudentDepartment(Connection connection, Scanner scanner) throws SQLException {
    System.out.println("Enter ID of student whose department to change:");
    int id = scanner.nextInt();
    scanner.nextLine(); // Consume newline
    System.out.println("Enter new department name:");
    String newDeptName = scanner.nextLine();

    // Check if the department exists
    if (checkDepartmentExists(connection, newDeptName)) {
        String updateStudentQuery = "UPDATE student SET dept_name = ? WHERE id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateStudentQuery);
        preparedStatement.setString(1, newDeptName);
        preparedStatement.setInt(2, id);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Student's department changed successfully.");
        } else {
            System.out.println("Failed to change student's department. Student not found.");
        }
    } else {
        System.out.println("Department does not exist.");
    }
}

// Function to remove a course
private static void removeCourse(Connection connection, Scanner scanner) throws SQLException {
    System.out.println("Enter Course ID to remove:");
    String courseId = scanner.next();
    String deleteCourseQuery = "DELETE FROM Course WHERE Course_ID = ?";
    PreparedStatement preparedStatement = connection.prepareStatement(deleteCourseQuery);
    preparedStatement.setString(1, courseId);
    int rowsAffected = preparedStatement.executeUpdate();
    if (rowsAffected > 0) {
        System.out.println("Course removed successfully.");
    } else {
        System.out.println("Failed to remove course. Course not found.");
    }
}

// Function to display the highest grade among students
private static void displayHighestGrade(Connection connection) throws SQLException {
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
}


// Function to add a classroom
private static void addClassroom(Connection connection, Scanner scanner) throws SQLException {
    System.out.println("Enter classroom details:");
    System.out.print("Building: ");
    String building = scanner.next();
    System.out.print("Room Number: ");
    String roomNo = scanner.next();
    System.out.print("Capacity: ");
    int capacity = scanner.nextInt();

    String insertClassroomQuery = "INSERT INTO classroom (building, room_no, capacity) VALUES (?, ?, ?)";
    PreparedStatement preparedStatement = connection.prepareStatement(insertClassroomQuery);
    preparedStatement.setString(1, building);
    preparedStatement.setString(2, roomNo);
    preparedStatement.setInt(3, capacity);
    int rowsAffected = preparedStatement.executeUpdate();
    if (rowsAffected > 0) {
        System.out.println("Classroom added successfully.");
    } else {
        System.out.println("Failed to add classroom.");
    }
}

// Function to display the capacity of all classrooms
private static void displayClassroomCapacity(Connection connection) throws SQLException {
    Statement statement = connection.createStatement();
    ResultSet resultSet = statement.executeQuery("SELECT * FROM classroom");

    System.out.println("Classroom Capacities:");
    System.out.println("-------------------------------------------------------------");

    // Print column headers
    System.out.printf("%-20s %-10s%n", "Building", "Capacity");

    // Print rows
    while (resultSet.next()) {
        String building = resultSet.getString("building");
        String roomNo = resultSet.getString("room_no");
        int capacity = resultSet.getInt("capacity");
        System.out.printf("%-20s %-10s%n", building + " - " + roomNo, capacity);
    }
}


// Function to add a course
private static void addCourse(Connection connection, Scanner scanner) throws SQLException {
    System.out.println("Enter course details:");
    System.out.print("Course ID: ");
    String courseId = scanner.next();
    scanner.nextLine(); // Consume newline
    System.out.print("Title: ");
    String title = scanner.nextLine();
    System.out.print("Department Name: ");
    String deptName = scanner.next();
    System.out.print("Credits: ");
    int credits = scanner.nextInt();

    // Check if the department exists
    if (checkDepartmentExists(connection, deptName)) {
        String insertCourseQuery = "INSERT INTO Course (Course_ID, Title, Dept_Name, Credits) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertCourseQuery);
        preparedStatement.setString(1, courseId);
        preparedStatement.setString(2, title);
        preparedStatement.setString(3, deptName);
        preparedStatement.setInt(4, credits);
        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Course added successfully.");
        } else {
            System.out.println("Failed to add course.");
        }
    } else {
        System.out.println("Department does not exist.");
    }
}
}



