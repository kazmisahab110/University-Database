import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateUniversitySchema {

    public static void main(String[] args) {
        // JDBC URL, username, and password of the database
        String url = "jdbc:mysql://localhost:3306/myuni";
        String username = "root";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            // Create department table
            String createDepartmentTable = "CREATE TABLE Department ("
                    + "Dept_name VARCHAR(50) PRIMARY KEY,"
                    + "Building VARCHAR(50),"
                    + "Budget INT"
                    + ")";
            statement.executeUpdate(createDepartmentTable);

            // Create instructor table
            String createInstructorTable = "CREATE TABLE Instructor ("
                    + "ID INT PRIMARY KEY,"
                    + "Name VARCHAR(50),"
                    + "Dept_Name VARCHAR(50),"
                    + "Salary INT,"
                    + "FOREIGN KEY (Dept_Name) REFERENCES Department(Dept_name)"
                    + ")";
            statement.executeUpdate(createInstructorTable);

            // Create course table
            String createCourseTable = "CREATE TABLE Course ("
                    + "Course_ID VARCHAR(10) PRIMARY KEY,"
                    + "Title VARCHAR(50),"
                    + "Dept_Name VARCHAR(50),"
                    + "Credits INT,"
                    + "FOREIGN KEY (Dept_Name) REFERENCES Department(Dept_name)"
                    + ")";
            statement.executeUpdate(createCourseTable);
            
             // Create classroom table
            String createClassroomTable = "CREATE TABLE Classroom ("
                    + "Building VARCHAR(50),"
                    + "Room_no VARCHAR(50),"
                    + "Capacity INT,"
                    + "PRIMARY KEY (Building, Room_no)"
                    + ")";
            statement.executeUpdate(createClassroomTable);

            // Create time_slot table
            String createTimeSlotTable = "CREATE TABLE Time_slot ("
                    + "Time_slot_id VARCHAR(50),"
                    + "Day VARCHAR(8),"
                    + "Start_time VARCHAR(15),"
                    + "End_time VARCHAR(15),"
                    + "PRIMARY KEY (Time_slot_id, Day, Start_time)"
                    + ")";
            statement.executeUpdate(createTimeSlotTable);

            // Create Section table
            String createSectionTable = "CREATE TABLE Section ("
                    + "Course_id VARCHAR(10),"
                    + "Sec_id VARCHAR(10),"
                    + "Semester VARCHAR(50),"
                    + "Year INT,"
                    + "Building VARCHAR(50),"
                    + "Room_no VARCHAR(50),"
                    + "Time_slot_id VARCHAR(50),"
                    + "PRIMARY KEY (Course_id, Sec_id, Semester, Year),"
                    + "FOREIGN KEY (Course_id) REFERENCES Course(Course_id),"
                    + "FOREIGN KEY (Building, Room_no) REFERENCES Classroom(Building, Room_no),"
                    + "FOREIGN KEY (Time_slot_id) REFERENCES Time_slot(Time_slot_id)"
                    + ")";
            statement.executeUpdate(createSectionTable);

            // Create teaches table
            String createTeachesTable = "CREATE TABLE Teaches ("
                    + "Id INT,"
                    + "Course_id VARCHAR(10),"
                    + "Sec_id VARCHAR(10),"
                    + "Semester VARCHAR(50),"
                    + "Year INT,"
                    + "PRIMARY KEY (Id, Course_id, Sec_id, Semester, Year),"
                    + "FOREIGN KEY (Id) REFERENCES Instructor(Id),"
                    + "FOREIGN KEY (Course_id, Sec_id, Semester, Year) REFERENCES Section(Course_id, Sec_id, Semester, Year)"
                    + ")";
            statement.executeUpdate(createTeachesTable);

            // Create student table
            String createStudentTable = "CREATE TABLE Student ("
                    + "Id INT PRIMARY KEY,"
                    + "Name VARCHAR(50),"
                    + "Dept_name VARCHAR(50),"
                    + "Tot_Cred INT,"
                    + "FOREIGN KEY (Dept_name) REFERENCES Department(Dept_name)"
                    + ")";
            statement.executeUpdate(createStudentTable);

            // Create takes table
            String createTakesTable = "CREATE TABLE Takes ("
                    + "Id INT,"
                    + "Course_id VARCHAR(10),"
                    + "Sec_id VARCHAR(10),"
                    + "Semester VARCHAR(50),"
                    + "Year INT,"
                    + "Grade VARCHAR(10),"
                    + "PRIMARY KEY (Id, Course_id, Sec_id, Semester, Year),"
                    + "FOREIGN KEY (Id) REFERENCES Student(Id),"
                    + "FOREIGN KEY (Course_id, Sec_id, Semester, Year) REFERENCES Section(Course_id, Sec_id, Semester, Year)"
                    + ")";
            statement.executeUpdate(createTakesTable);

            // Create advisor table
            String createAdvisorTable = "CREATE TABLE Advisor ("
                    + "S_id INT,"
                    + "I_id INT,"
                    + "PRIMARY KEY (S_id, I_id),"
                    + "FOREIGN KEY (S_id) REFERENCES Student(Id),"
                    + "FOREIGN KEY (I_id) REFERENCES Instructor(Id)"
                    + ")";
            statement.executeUpdate(createAdvisorTable);

            // Create prereq table
            String createPrereqTable = "CREATE TABLE Prereq ("
                    + "Course_id VARCHAR(10),"
                    + "Pre_req_id VARCHAR(10),"
                    + "PRIMARY KEY (Course_id, Pre_req_id),"
                    + "FOREIGN KEY (Course_id) REFERENCES Course(Course_id)"
                    + ")";
            statement.executeUpdate(createPrereqTable);


            System.out.println("University schema created successfully!");
            
            
            
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
