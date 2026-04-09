# School Management System

A robust, console-based CRUD application developed in Java, utilizing JDBC for seamless MySQL database integration. This system is designed to manage student and teacher records efficiently with automated analytics and data sorting.

## Features
* **Complete CRUD Operations:** Create, Read, Update, and Delete records for both students and teachers.
* **Automated Analytics:** Utilizes SQL aggregate functions to generate real-time database statistics (e.g., total registered students).
* **Smart Sorting:** Automatically sorts queried data (e.g., retrieving students ordered by highest marks descending).
* **Data Persistence:** Fully integrated with a local MySQL database ensuring data integrity and session persistence.

## Technologies Used
* **Language:** Java (JDK 11 or higher recommended)
* **Database:** MySQL Server
* **API:** Java Database Connectivity (JDBC)
* **IDE/Environment:** Visual Studio Code / Terminal

## Setup and Installation

### 1. Database Configuration
Before running the application, you must configure your local MySQL server.
1. Open MySQL Workbench or your preferred SQL terminal.
2. Create a new database named `school_db`.
3. Open `models/DatabaseHandler.java` and update the database credentials to match your local environment:
   - `URL`
   - `USER`
   - `PASSWORD`

*(Note: The application includes an `initializeDb()` method that will automatically generate the required `students` and `teachers` tables upon first execution.)*

### 2. Execution via Shell Script (Recommended)
Configuring the Java classpath for JDBC drivers manually in VS Code can be error-prone. To streamline execution, a shell script (`run.sh`) is included in the root directory. This script handles compilation and executes the program with the correct classpath arguments automatically.

**For Mac/Linux users:**
Open your terminal, navigate to the project root, and execute:
```bash
./run.sh