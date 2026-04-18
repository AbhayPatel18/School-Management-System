import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;
import models.DatabaseHandler;

public class SchoolApp {
   public static boolean authenticateAdmin(Scanner sc) {
      String correctUser = "admin";
      String correctPass = "java123";
      int attempts = 3;

      System.out.println("\n======= ADMIN PORTAL =======");

      while (attempts > 0) {
         System.out.print("Username: ");
         String user = sc.nextLine();

         System.out.print("Password: ");
         String pass = sc.nextLine();

         if (user.equals(correctUser) && pass.equals(correctPass)) {
            System.out.println("Login Successful! Welcome, Admin.");
            return true;
         } else {
            attempts--;
            System.out.println("Access Denied! Incorrect username or password.");
            System.out.println("Attempts remaining: " + attempts + "\n");
         }
      }

      return false;
   }

   public static void adminDashboard(Scanner sc) {
      System.out.println("------------------------------------------------");

      boolean isRunning = true;

      System.out.println("-------Welcome to School Management System-------");
      while (isRunning) {
         System.out.println("\n--- MENU ---");
         System.out.println("1. Add Student");
         System.out.println("2. Add Teacher");
         System.out.println("3. Show All Students");
         System.out.println("4. Show All Teachers");
         System.out.println("5. Search Student (using Roll no.)");
         System.out.println("6. Search Student (using name)");
         System.out.println("7. Delete Student (using Roll no.)");
         System.out.println("8. Update Student (using Roll no.)");
         System.out.println("9. Show total Student Count");
         System.out.println("10.Show Topper List");
         System.out.println("11.Exit");

         try {
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
               case 1:
                  System.out.print("Enter Name:- ");
                  String stdName = sc.nextLine();
                  System.out.print("Enter City:- ");
                  String stdCity = sc.nextLine();
                  System.out.print("Enter Marks:- ");
                  double marks = sc.nextDouble();
                  sc.nextLine();

                  while (marks < 0 || marks > 100) {
                     System.out.println("Invalid marks ! , Marks must be between 0 & 100.");
                     System.out.println("Please Enter Marks again :- ");
                     marks = sc.nextDouble();
                     sc.nextLine();
                  }

                  DatabaseHandler.insertStudent(stdName, stdCity, marks);
                  System.out.println("Student Added!");

                  try {
                     FileWriter pen = new FileWriter("AuditLog.txt", true);
                     pen.write("New Student Added:- " + stdName + " with Marks:- " + marks + "\n");

                     pen.close();

                  } catch (IOException e) {
                     System.out.println("Could NOT write into the Log File.");
                  }

                  break;
               case 2:
                  System.out.print("Enter Name:- ");
                  String trName = sc.nextLine();
                  System.out.print("Enter City:- ");
                  String trCity = sc.nextLine();
                  System.out.print("Enter Salary:- ");
                  double salary = sc.nextDouble();
                  sc.nextLine();
                  System.out.print("Enter Subject:- ");
                  String trSubject = sc.nextLine();

                  DatabaseHandler.insertTeacher(trName, trCity, salary, trSubject);
                  System.out.println("Teacher Added!");

                  break;

               case 3:
                  DatabaseHandler.getAllStudents();
                  break;
               case 4:
                  DatabaseHandler.getAllTeachers();
                  break;
               case 5:
                  System.out.print("Enter Roll no. :- ");
                  int roll = sc.nextInt();

                  DatabaseHandler.getStudentByRollno(roll);
                  break;
               case 6:
                  System.out.print("Enter name or partial name to search:- ");
                  String searchKeyword = sc.nextLine();
                  DatabaseHandler.searchStudentByName(searchKeyword);
                  break;

               case 7:
                  System.out.print("Enter Roll no. :- ");
                  roll = sc.nextInt();
                  DatabaseHandler.deleteStudent(roll);

                  try {
                     FileWriter pen = new FileWriter("AuditLog.txt", true);
                     pen.write("Existing Student Deleted , of Rollno:- " + roll + "\n");

                     pen.close();

                  } catch (IOException e) {
                     System.out.println("Could NOT write into the Log File.");
                  }
                  break;
               case 8:
                  System.out.print("Enter Roll no. of student to update: ");
                  int updateRoll = sc.nextInt();
                  sc.nextLine(); // Clear scanner buffer

                  System.out.print("Enter New Name: ");
                  String newName = sc.nextLine();

                  System.out.print("Enter New City: ");
                  String newCity = sc.nextLine();

                  System.out.print("Enter New Marks: ");
                  double newMarks = sc.nextDouble();
                  sc.nextLine(); // Clear scanner buffer

                  try {
                     FileWriter pen = new FileWriter("AuditLog.txt", true);
                     pen.write("Existing Student Updated :- " + newName + " with Marks:- " + newMarks + "\n");

                     pen.close();

                  } catch (IOException e) {
                     System.out.println("Could NOT write into the Log File.");
                  }

                  DatabaseHandler.updateStudent(updateRoll, newName, newCity, newMarks);
                  break;
               case 9:
                  DatabaseHandler.getTotalStudentsCount();
                  break;
               case 10:
                  DatabaseHandler.showTopStudents();
                  break;
               case 11:
                  System.out.println("Thanks for using this program , Exiting the program .... ");
                  isRunning = false;
                  break;

               default:
                  System.out.println("Invalid Choice, Please try again :(");
                  break;

            }
         } catch (InputMismatchException e) {
            System.out.println("Please Enter Number Only !!");
            sc.nextLine();
         }

      }

   }

   public static void studentDashboard(Scanner sc) {
      int rollno = 0;
      String name = "";
      try {
         System.out.print("Enter UserName(as Rollno.):- ");
         rollno = sc.nextInt();
         sc.nextLine();
         System.out.print("Enter Password(as Name):- ");
         name = sc.nextLine();
      } catch (InputMismatchException e) {
         System.out.println("Please Enter valid Username & password :(");
      }
      try {

         if (DatabaseHandler.verifyStudent(rollno, name)) {

            boolean studentRunning = true;

            while (studentRunning) {
               System.out.println("1.See your Profile.");
               System.out.println("2.View Top 3 Students.");
               System.out.println("3.Exit");
               System.out.print("Enter your choice:- ");
               int choice = sc.nextInt();

               switch (choice) {

                  case 1:
                     DatabaseHandler.getStudentByRollno(rollno);
                     break;
                  case 2:
                     DatabaseHandler.showTopStudents();
                     break;
                  case 3:
                     studentRunning = false;
                     break;
                  default:
                     System.out.println("Please Enter valid choice !!");

               }
            }

         }
         else{System.out.println("Login Failed: Incorrect Roll Number or Name.");

         }

      } catch (SQLException e) {
         e.printStackTrace();
      }

      sc.nextLine();
   }

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);
      int user = 0;
      try {

         System.out.println("Welcome , Who is logging in?");
         System.out.println("1.Admin");
         System.out.println("2.Student");
         System.out.print("Enter 1 or 2 :- ");
         user = sc.nextInt();
         sc.nextLine();

      } catch (InputMismatchException e) {
         System.out.println("Please Enter valid input, 1 or 2");
         sc.nextLine();
      }

      System.out.println("Connecting to Database ....");
      DatabaseHandler.initializeDb();

      if (user == 1)
         adminDashboard(sc);
      else
         studentDashboard(sc);

      return;

   }
}
