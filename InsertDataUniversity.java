import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InsertDataUniversity {

    public static void main(String[] args) {
        // JDBC URL, username, and password of the database
        String url = "jdbc:mysql://localhost:3306/myuni";
        String username = "root";
        String password = "password";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            // Insert data into department table
            String insertDepartmentData = "INSERT INTO department (dept_name, building, budget) VALUES " +
                    "('Computer Science', 'Hazen Hall', 100000), " +
                    "('Mathematics', 'Ganong Hall', 80000), " +
                    "('English', 'Commons Building', 90000), " +
                    "('Business', 'Oland Hall', 70000), " +
                    "('Biology', 'Hazen Hall', 120000)";
            statement.executeUpdate(insertDepartmentData);

            // Insert data into instructor table
            String insertInstructorData = "INSERT INTO instructor (ID, Name, Dept_Name, Salary) VALUES " +
                    "(101, 'Jeff Mcnally', 'Computer Science', 60000), " +
                    "(102, 'Hamdan', 'Mathematics', 55000), " +
                    "(103, 'Margaret', 'English', 40000), " +
                    "(104, 'Ali', 'Business', 39000), " +
                    "(105, 'Anna', 'Biology', 50000)";
            statement.executeUpdate(insertInstructorData);

            // Insert data into Course table
            String insertCourseData = "INSERT INTO Course (Course_ID, Title, Dept_Name, Credits) VALUES " +
                    "('CS1073', 'Introduction to Computer Science', 'Computer Science', 4), " +
                    "('MATH101', 'Calculus I', 'Mathematics', 4), " +
                    "('ENG101', 'English Composition', 'English', 3), " +
                    "('BA1501', 'Intro to business', 'Business', 3), " +
                    "('BIO101', 'Human Anatomy', 'Biology', 4), " +
                    "('Cs1083', 'Intro to Java', 'Computer Science', 4)";
            statement.executeUpdate(insertCourseData);

            // Insert data into Classroom table
            String insertClassroomData = "INSERT INTO Classroom (building, room_no, capacity) VALUES " +
                    "('Hazen Hall', '101', 50), " +
                    "('Ganong Hall', '202', 40), " +
                    "('Commons Building', '301', 60), " +
                    "('Hazen Hall', '401', 40)";
            statement.executeUpdate(insertClassroomData);

            // Insert data into Time_slot table
            String insertTimeSlotData = "INSERT INTO Time_slot (time_slot_id, day, start_time, end_time) VALUES " +
                    "('MWF8AM', 'M/W/F', '08:00 AM', '09:00 AM'), " +
                    "('TTH10AM', 'T/Th', '10:00 AM', '11:30 AM'), " +
                    "('MWF11AM', 'M/W/F', '11:00 AM', '12:00 PM')";
            statement.executeUpdate(insertTimeSlotData);

            // Insert data into Section table
            String insertSectionData = "INSERT INTO Section (Course_id, sec_id, semester, year, building, room_no, time_slot_id) VALUES " +
                    "('CS1073', '01', 'Fall', 2023, 'Hazen Hall', '101', 'MWF8AM'), " +
                    "('MATH101', '01', 'Spring', 2024, 'Ganong Hall', '202', 'TTH10AM'), " +
                    "('ENG101', '01', 'Fall', 2023, 'Commons Building', '301', 'MWF11AM'), " +
                    "('BIO101', '01', 'Fall', 2023, 'Hazen Hall', '401', 'MWF11AM'), " +
                    "('BA1501', '01', 'Fall', 2023, 'Hazen Hall', '401', 'MWF11AM')";
            statement.executeUpdate(insertSectionData);

            // Insert data into teaches table
            String insertTeachesData = "INSERT INTO teaches (id, course_id, sec_id, semester, year) VALUES " +
                    "(101, 'CS1073', '01', 'Fall', 2023), " +
                    "(102, 'MATH101', '01', 'Spring', 2024), " +
                    "(103, 'ENG101', '01', 'Fall', 2023), " +
                    "(104, 'BA1501', '01', 'Fall', 2023), " +
                    "(105, 'BIO101', '01', 'Fall', 2023) " ;
            statement.executeUpdate(insertTeachesData);

            // Insert data into student table
            String insertStudentData = "INSERT INTO student (id, name, dept_name, tot_Cred) VALUES " +
                    "(1001, 'Alice Johnson', 'Computer Science', 90), " +
                    "(1002, 'Bob Smith', 'Mathematics', 85), " +
                    "(1003, 'Emily Brown', 'English', 95), " +
                    "(1004, 'Maninder Singh', 'Business', 70), " +
                    "(1005, 'Kim', 'Biology', 100)";
            statement.executeUpdate(insertStudentData);

            // Insert data into takes table
            String insertTakesData = "INSERT INTO takes (id, course_id, sec_id, semester, year, grade) VALUES " +
                    "(1001, 'CS1073', '01', 'Fall', 2023, 'A'), " +
                    "(1002, 'MATH101', '01', 'Spring', 2024, 'B+'), " +
                    "(1003, 'ENG101', '01', 'Fall', 2023, 'A-'), " +
                    "(1004, 'BA1501', '01', 'Fall', 2023, 'B-'), " +
                    "(1005, 'BIO101', '01', 'Fall', 2023, 'A-')";
            statement.executeUpdate(insertTakesData);

            // Insert data into advisor table
            String insertAdvisorData = "INSERT INTO advisor (s_id, i_id) VALUES " +
                    "(1001, 101), " +
                    "(1002, 102), " +
                    "(1003, 103), " +
                    "(1004, 104), " +
                    "(1005, 105)";
            statement.executeUpdate(insertAdvisorData);

            // Insert data into prereq table
            String insertPrereqData = "INSERT INTO prereq (course_id, pre_req_id) VALUES " +
                    "('CS1083', 'CS1073'), " +
                    "('Cs1083', 'MATH101')";
            statement.executeUpdate(insertPrereqData);

            System.out.println("Data inserted successfully!");
            
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
