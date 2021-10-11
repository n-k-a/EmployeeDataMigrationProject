# EmployeeDataMigrationProject
 ## Synopsis
 This is an data handling project developed as part of my sparta global Java course.
 This is a project that works by being able to use a bufferedreader in order to pul files from CSV files and store them in an object that stimulates their order and datatypes. In turn these can be placed into a database through the use of JDBC in order to create a persistent table which permamently contains these files and can be retrieved using SQL statements into the java project. Basic threading was also implemented in order to speed up the process of writing to DB

## File Structure


## Running the project
In order to run this project, you need 
-Java JDK 14
-DB Browser/SQLite as this was the db method used for this project
-the .csv files "EmployeeRecordsLarge" and "EmployeeRecords"
-a .db File
To run the project, make sure that "EmployeeDataMigrationProjectMain" is selected as the file to run.
Once these steps have been resolved, all that is needed to be done is to follow the steps given by the console line in order to execute what is suggested.

## What could be done to improve it
-More unit testing for threads and db related processes, as this would've helped with testing.
-The usage of a DB connection pool instead of just a method which calls the Connection, as this is more efficient. DataSources mainly.
-Better logging implementation. The current state is barebones and faulty
-More testing with the UI. This was one of the last things developed and this lacks some of the things that would've made it more efficient (such as a proper model/view/controller)
-Better methods with the SQL method execution. A stronger way of utilising both first and last name as search parameters is a start
-Proper package creatin.
