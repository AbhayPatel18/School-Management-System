import java.util.Scanner;
import models.DatabaseHandler;

public class SchoolApp {

   public static void main(String[] args) {
      Scanner sc = new Scanner(System.in);

      System.out.println("Connecting to Database ....");
      DatabaseHandler.initializeDb();
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
         System.out.println("6. Delete Student (using Roll no.)");
         System.out.println("7. Update Student (using Roll no.)");
         System.out.println("8. Show total Student Count");
         System.out.println("9. Exit");
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
               DatabaseHandler.insertStudent(stdName, stdCity, marks);
               System.out.println("Student Added!");

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
               System.out.print("Enter Roll no. :- ");
               roll = sc.nextInt();
               DatabaseHandler.deleteStudent(roll);
               break;
            case 7:
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

               DatabaseHandler.updateStudent(updateRoll, newName, newCity, newMarks);
               break;
            case 8:
               DatabaseHandler.getTotalStudentsCount();
               break;
            case 9:
               System.out.println("Thanks for using this program , Exiting the program .... ");
               isRunning = false;
               break;

            default:
               System.out.println("Invalid Choice, Please try again :(");
               break;

         }

      }
      sc.close();
   }
}
