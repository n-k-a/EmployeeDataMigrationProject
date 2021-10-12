# EmployeeDataMigrationProject
## Synopsis
 This is an data handling project developed as part of my sparta global Java course.
 This is a project that works by being able to use a bufferedreader in order to pul files from CSV files and store them in an object that stimulates their order and datatypes. In turn these can be placed into a database through the use of JDBC in order to create a persistent table which permamently contains these files and can be retrieved using SQL statements into the java project. Basic threading was also implemented in order to speed up the process of writing to DB

## File Structure
### jdbcDriver
This class handles db connection. It has a method to drop an existing table and create a new table which uses employee's instance variables as headers, a method to just fill in an existing table, a method to retrieve records which have a specific ID as a criteria, or the same last name.
The class also has a method which calls the DB connection.
### Employee retrieval
This handles taking the values from the CSV, cleanining it and interacting with the JDBC driver
### CSV Handler
This implements runnable and tries to create threads which handle the CSV and storage for the other classes. 
### RegexApplier
This class mostly focuses on string validation. There are strings which store the patterns for emails/names/initials.
For each pattern, there's a class which forces  a matcher. This is used for the retrival class/
### Employee
This class is what handles the projects storage the data from the CSV files and from the database.
The main thing regarding it is that it has a modified toString method(overriden to print out the variables to string), and an overriden hashcode and equals. This is just changed so that it returns only the id. This is used for the duplicate check with the CSV pulled files.

## How to use
Here are the steps:
1. Once project has run, it will automatically go through the CSVs to put into Vectors
2. Once done, it will ask the thread that finished first if it wants to choose between erasing the current DB and filling a new one with the Vector  values, before asking the same for the slower one
3. Once either choice has been selected, it will then ask if you want to retrieve records from the table using either the ID or LastName as parameters.
4. Once these have all ran, the threads should exit and the program ends.

## Running the project
In order to run this project, you need:
- Java JDK 14
- DB Browser/SQLite as this was the db method used for this project
- the .csv files "EmployeeRecordsLarge" and "EmployeeRecords"
- a .db File
To run the project, make sure that "EmployeeDataMigrationProjectMain" is selected as the file to run.
Once these steps have been resolved, all that is needed to be done is to follow the steps given by the console line in order to execute what is suggested.

##Testing
There are tests done for this project in order to test the functionality.
- checkRegexInitial, this checks how the regex handles the middle initial and sees whether it assets true a normal initial value, and assets false a numerical one
- checkRegexName, which checks if the regex check works for names, with the test seeing whether it asserts true for a normal name, and assets false for a numeric flled string or punctuation using only
- checkRegexEmail, which checks if the email matches the standard "xasdd@dsd.dsd" format, and checks with a correct email and one which doesn't have an '@'.
- checkEmpIDNotEquals uses two almost identical Employee classes outside of the IDs being different, and sees whether an assertfalse happens
- checkEmpIDEquals, checks whether it recognises two objects which only share the same ID.
- checkEmpEquals, which checks whether the equals() method has been overwritten and can compare two identical objects

## What could be done to improve it
- More unit testing for threads and db related processes, as this would've helped with testing.
- The usage of a DB connection pool instead of just a method which calls the Connection, as this is more efficient. DataSources mainly.
- Better logging implementation. The current state is barebones and faulty
- More testing with the UI. This was one of the last things developed and this lacks some of the things that would've made it more efficient (such as a proper model/view/controller) or tests for failed inputs
- Better methods with the SQL method execution. A stronger way of utilising both first and last name as search parameters is a start
- Proper package creation.
- Implementation of Lambdas. Time constraints limited this section from being properly done.
- More regex tests for date and salary and Prefixes
- Proper tests with null values.
- Issue with drop being run in one thread and insert in another leading to errors in the second due to null values
- Issues with scanner inputs and prompts being msessed up due to the way that threads were integrated
- adding more threads.
- Understanding thread concepts as to prevent issues such as the retrieval of null values from happening.
If there are issues with this commit, please look at the last one with no threads at all.
