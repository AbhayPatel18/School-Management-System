import java.io.BufferedReader;
import java.io.BufferedWriter; // for writing in file
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter; // for creating the file 
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import models.*;

public class SchoolApp_V1_memory {

   // Saving the file
   public static void SaveFile(ArrayList<Person> list) {
      try {
         BufferedWriter studentPen = new BufferedWriter(new FileWriter("StudentData.csv"));
         BufferedWriter teacherPen = new BufferedWriter(new FileWriter("TeacherData.csv"));

         for (Person p : list) {
            if (p instanceof Student) {
               Student s = (Student) p;
               String line = (p.getName() + "," + p.getCity() + "," + s.getMarks());

               studentPen.write(line);
               studentPen.newLine();
            } else if (p instanceof Teacher) {
               Teacher t = (Teacher) p;
               String line = (p.getName() + "," + p.getCity() + "," + t.getSalary() + "," + t.getSubject());

               teacherPen.write(line);
               teacherPen.newLine();
            }
         }
         studentPen.close();
         teacherPen.close();

         System.out.println("Data Saved Successfully to Student.csv & Teacher.csv");
      } catch (IOException e) {
         System.out.println("Error in Saving the Data :( " + e.getMessage());
      } catch (Exception e) {
         System.out.println("Something Went Wrong! " + e.getMessage());
      }
   }

   // Loading file
   public static void loadFile(ArrayList<Person> list) {
      try {
         File f = new File("StudentData.csv");
         if (f.exists()) {
            BufferedReader studentRead = new BufferedReader(new FileReader(f));
            String line;
            while ((line = studentRead.readLine()) != null) {
               String[] data = line.split(",");
               // adding data according to Student(name,city,marks)
               list.add(new Student(data[0], data[1], Double.parseDouble(data[2])));
            }
            studentRead.close();
         }

      } catch (Exception e) {
         System.out.println("Error loading Student Data :(" + e.getMessage());
      }
      try {
         File f = new File("TeacherData.csv");
         if (f.exists()) {
            BufferedReader teacherRead = new BufferedReader(new FileReader(f));
            String line;
            while ((line = teacherRead.readLine()) != null) {
               String[] data = line.split(",");
               // adding data according to Teacher(name,city,salary,subject)
               list.add(new Teacher(data[0], data[1], Double.parseDouble(data[2]), data[3]));
            }
            teacherRead.close();
         }

      } catch (Exception e) {
         System.out.println("Error loading Teacher Data :(" + e.getMessage());
      }
      System.out.println("DataBase loaded . Total Records: " + list.size());
   }

   // Searching Person from the list
   public static void searchPerson(ArrayList<Person> list, Scanner sc) {
      System.out.print("Enter the name to Search:- ");
      String query = sc.nextLine().trim();

      boolean found = false; // flag

      System.out.println("\n------Search Result------");
      for (Person p : list) {
         if (p.getName().toLowerCase().contains(query.toLowerCase())) {
            if (p instanceof Student) {
               Student s = (Student) p;
               System.out.println("Found: Student, Name:- " + p.getName() + " |City:- " + p.getCity() + " |Rollno:- "
                     + s.getRollno() + " |Marks:- " + s.getMarks());
            } else if (p instanceof Teacher) {
               Teacher t = (Teacher) p;
               System.out.println("Found: Teacher, Name:- " + p.getName() + " |City:- " + p.getCity() + " |Salary:- "
                     + t.getSalary() + " |Subject:- " + t.getSubject());
            }

            found = true;
         }

      }
      if (!found) {
         System.out.println("There is no record matching: " + query);
      }

   }

   // delete a Person from the list
   public static void deletePerson(ArrayList<Person> list, Scanner sc) {
      System.out.print("Enter the Person you want to Delete: ");
      String query = sc.nextLine();

      Person toRemove = null;
      for (Person p : list) {
         if (p.getName().toLowerCase().contains(query.toLowerCase())) {
            System.out.println("Found & Deleted : " + query);
            toRemove = p;
            break;
         }
      }
      if (toRemove != null) {
         list.remove(toRemove);
         System.out.println("Record permanently removed ");
      } else {
         System.out.println("Person not found !!");
      }
   }

   // Showing Topper's List
   public static void showTopper(ArrayList<Person> list) {
      ArrayList<Student> studentList = new ArrayList<>();
      for (Person p : list) {
         if (p instanceof Student) {
            studentList.add((Student) p);
         }
      }
      Collections.sort(studentList, (s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()));

      System.out.println("-----Topper's List-----");
      int rank = 1;
      for (Student s : studentList) {
         System.out.println("Rank: " + rank + " | Name: " + s.getName() + " | Marks: " + s.getMarks());
         rank++;
      }

   }
      //updating deatails in person !!
   public static void updatePerson(ArrayList<Person> list, Scanner sc) {
      System.out.print("Enter Name to Update:- ");
      String query = sc.nextLine();

      boolean found = false;
      for (Person p : list) {

         if (p.getName().toLowerCase().contains(query.toLowerCase())) {

            System.out.print("what do you want to edit (1) city and (2) marks/salary:- ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
               // city (common for Student and Teacher)
               System.out.print("Enter new city to edit:- ");
               String newCity = sc.nextLine();
               p.setCity(newCity);
               System.out.println("City is updated !");
            } else if (choice == 2) {
               // marks or salary
               if (p instanceof Student) {
                  System.out.println("Enter new marks:- ");
                  double newMarks = sc.nextDouble();
                  ((Student) p).setMarks(newMarks);
               } else if (p instanceof Teacher) {
                  System.out.println("Enter new Salary:- ");
                  double newSalary = sc.nextDouble();
                  ((Teacher) p).setSalary(newSalary);
               }
               System.out.println(" value Updated !!");

            } else {
               System.out.println("Invalid choice !!");
               return;
            }

         }
         found = true;
         break;

      }
      if (!found) {
         System.out.println("Person not found :( ");
      }
   }

   public static void main(String[] args) {
      ArrayList<Person> peopleList = new ArrayList<>();
      Scanner sc = new Scanner(System.in);
      System.out.println("loading Data......");
      loadFile(peopleList);
      boolean isRunning = true;

      System.out.println("-------Welcome to School Management System-------");
      while (isRunning) {
         System.out.println("\n--- MENU ---");
         System.out.println("1. Add Student");
         System.out.println("2. Add Teacher");
         System.out.println("3. Show All Details");
         System.out.println("4. Search Record");
         System.out.println("5. Delete Record");
         System.out.println("6. Update Person");
         System.out.println("7. Show Topper's List");
         System.out.println("8. Exit");
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
               Student tempS = new Student(stdName, stdCity, marks);
               peopleList.add(tempS);
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
               Teacher tempTr = new Teacher(trName, trCity, salary, trSubject);
               peopleList.add(tempTr);
               System.out.println("Teacher Added!");

               break;
            case 3:
               System.out.println(
                     "----------------------------------------School Details----------------------------------------");
               System.out.printf("%-10s| %-4s | %-20s | %-15s | %-20s | %-7.3s | %n",
                     "ROLE", "ID", "NAME", "CITY", "SUBJECT", "MKS/SAL");
               for (Person p : peopleList) {
                  if (p instanceof Displayable) {
                     ((Displayable) p).displayDetails();
                  }
               }
               break;
            case 4:
               searchPerson(peopleList, sc);
               break;
            case 5:
               deletePerson(peopleList, sc);
               break;
            case 6:
               updatePerson(peopleList, sc);
               break;
            case 7:
               showTopper(peopleList);
               break;
            case 8:
               System.out.println("Saving the Details .....");
               SaveFile(peopleList);
               System.out.println("Exiting the Program . Goodbye !!");
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