# 🏫 School Management System

A core Java console application demonstrating complete CRUD operations using **JDBC** and **MySQL**. 

This project was built from scratch to transition from basic in-memory data structures to a robust, persistent database architecture.

## 🚀 Features
- **Create:** Add new students and teachers to the database.
- **Read:** View all records in a formatted table or search for specific students by their unique Roll No.
- **Delete:** Safely remove records with built-in validation checks to prevent crashes.
- *Update: (Coming Soon!)*

## 🛠️ Tech Stack
- **Language:** Java
- **Database:** MySQL
- **API:** JDBC (Java Database Connectivity)
- **Concepts Applied:** Object-Oriented Programming, `try-with-resources`, SQL `PreparedStatement` logic.

## 🗄️ Database Schema
The system relies on a MySQL database with the following core structure:

**Table: `students`**
| Column | Type | Description |
| :--- | :--- | :--- |
| `roll_no` | INT (Primary Key) | Auto-incremented unique ID |
| `name` | VARCHAR(50) | Student's full name |
| `city` | VARCHAR(50) | Student's city of residence |
| `marks` | DOUBLE | Academic score |

*(Note: The `teachers` table follows a similar structure tracking salary and subject).*

## 💻 How to Run (Locally)
1. Clone this repository to your local machine.
2. Ensure you have MySQL running and the `mysql-connector-j` jar file included in your classpath.
3. Update the `DatabaseHandler.java` file with your local MySQL credentials (username/password).
4. Run the `run.sh` script or compile and execute `SchoolApp.java` from your terminal.

## 🗺️ Roadmap
- [x] Establish secure JDBC MySQL connection
- [x] Complete Create, Read, Delete (CRD) operations
- [x] Implement Update (U) operation for full CRUD
- [ ] Integrate Custom Exception Handling for invalid data entry
- [ ] Migrate from Console Application to Graphical User Interface (GUI) using Java Swing
