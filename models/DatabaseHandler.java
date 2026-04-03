package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/school_db";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    /*
     * public static Connection connect() {
     * Connection con = null;
     * try {
     * con = DriverManager.getConnection(URL, USER, PASSWORD);
     * } catch (SQLException e) {
     * System.out.println("DataBase Connection failed , " + e);
     * } catch (Exception e) {
     * System.out.println("Something went wwrong in connecting the Database !!" +
     * e);
     * }
     * return con; //returning null
     * }
     */
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDb() {
        String studentSql = "CREATE TABLE IF NOT EXISTS students (" +
                "rollno INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, " +
                "city VARCHAR(50), " +
                "marks DOUBLE)";

        String teacherSql = "CREATE TABLE IF NOT EXISTS teachers (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(100) NOT NULL, "+
                "city VARCHAR(50), " +
                "subject VARCHAR(50), " +
                "salary DOUBLE)";

        try (Connection con = connect();
                Statement stmt = con.createStatement()) {

            stmt.execute(studentSql);
            stmt.execute(teacherSql);
            System.out.println("Tables initialized Successfully !!");

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("Something went wrong !!" + e);
        }
    }

    // Inserting Student
    public static void insertStudent(String name, String city, double marks) {
        String sqlQuery = "INSERT INTO students (name, city, marks) VALUES (?, ?, ?)";

        try (Connection con = connect();
                PreparedStatement pstmt = con.prepareStatement(sqlQuery)) {

            pstmt.setString(1, name);
            pstmt.setString(2, city);
            pstmt.setDouble(3, marks);

            pstmt.executeUpdate();
            System.out.println(" Student Saved: " + name);

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("Something went wrong While Inserting Student ," + e);
        }
    }

    // Inserting Teacher
    public static void insertTeacher(String name, String city, double salary, String subject) {
        String sql = "INSERT INTO teachers (name, city, salary, subject) VALUES (?, ?, ?, ?)";

        try (Connection con = connect();
                PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, name);
            pstmt.setString(2, city);
            pstmt.setDouble(3, salary);
            pstmt.setString(4, subject);

            pstmt.executeUpdate();
            System.out.println("Teacher Saved: " + name);

        } catch (SQLException e) {
            System.out.println(e);
        } catch (Exception e) {
            System.out.println("Something went wrong While Inserting Student ," + e);
        }

    }

    // details of all students
    public static void getAllStudents() {
        String sqlQuery = "SELECT * FROM students";

        try (Connection con = connect();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery)) {

            System.out.println(
                    "----------------------------------------Student Details----------------------------------------");
            System.out.printf("%-4s | %-20s | %-15s | %-7s | %n",
                    "ROLL NO", "NAME", "CITY", "MARKS");

            // retrieving data from tables

            while (rs.next()) {
                int r = rs.getInt("rollno");
                String n = rs.getString("name");
                String c = rs.getString("city");
                Double m = rs.getDouble("marks");

                System.out.printf("%-4s | %-20s | %-15s | %-7.2s | %n", r, n, c, m);

            }
            System.out.println("-----------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // details of All teachers
    public static void getAllTeachers() {
        String sqlQuery = "SELECT * FROM teachers";

        try (Connection con = connect();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sqlQuery)) {

            System.out.println(
                    "----------------------------------------Teacher Details----------------------------------------");
            System.out.printf("%-4s | %-20s | %-15s | %-7s | %-20s | %n",
                    "ID", "NAME", "CITY", "SALARY", "SUBJECT");

            // retrieving data from tables

            while (rs.next()) {
                int ID = rs.getInt("id");
                String n = rs.getString("name");
                String c = rs.getString("city");
                Double sal = rs.getDouble("salary");
                String sub = rs.getString("subject");

                System.out.printf("%-4s | %-20s | %-15s | %-7.2s | %-20s | %n", ID, n, c, sal, sub);

            }
            System.out.println("-----------------------------------------------------");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Searching Students by Rollno
    public static boolean getStudentByRollno(int rollno) {
        String sqlQuery = "Select * FROM students where rollno = ?"; // ? -> placeholder

        try (Connection con = connect();
                PreparedStatement pstmt = con.prepareStatement(sqlQuery)) {

            pstmt.setInt(1, rollno);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // If rs.next() is true, the student exists!
                System.out.println("\n----- STUDENT FOUND -----");
                System.out.println("Name:  " + rs.getString("name"));
                System.out.println("City:  " + rs.getString("city"));
                System.out.println("Marks: " + rs.getDouble("marks"));
                System.out.println("------------------------");
                return true;
            } else {
                System.out.println("\n Student with Roll No " + rollno + " not found!");
                return false;

            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    //deleting students using rollno
    // --- DELETE STUDENT BY ID ---
    public static void deleteStudent(int rollNo) {
        String sql = "DELETE FROM students WHERE rollno = ?";

        try (Connection con = connect();
             PreparedStatement pstmt = con.prepareStatement(sql)) {
            
            pstmt.setInt(1, rollNo);
            
            // executeUpdate returns the number of rows affected
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Student (Roll No: " + rollNo + ") deleted successfully!");
            } else {
                System.out.println("Could not delete. Student ID " + rollNo + " does not exist.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // --- UPDATE STUDENT ---
    public static void updateStudent(int rollno, String newName, String newCity, double newMarks) {
        String sqlQuery = "UPDATE students SET name = ?, city = ?, marks = ? WHERE rollno = ?";

        try (Connection con = connect();
             PreparedStatement pstmt = con.prepareStatement(sqlQuery)) {

            pstmt.setString(1, newName);
            pstmt.setString(2, newCity);
            pstmt.setDouble(3, newMarks);
            pstmt.setInt(4, rollno); // Targets the specific student

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nSuccess: Student (Roll No: " + rollno + ") updated successfully!");
            } else {
                System.out.println("\nError: Student with Roll No " + rollno + " not found!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Something went wrong while updating student: " + e);
        }
    }
}
