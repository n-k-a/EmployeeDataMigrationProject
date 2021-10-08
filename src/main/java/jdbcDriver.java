import java.sql.*;
import java.util.Vector;

public class jdbcDriver {
private void transferToDB(Vector<Employee> el){
    try(Connection conn = DriverManager.getConnection("jdbc:sqlite:EmployeeRecords.db")){
        Statement statement = conn.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS Employee");
        statement.executeUpdate("CREATE TABLE Employee( " +
                "ID INTEGER NOT NULL PRIMARY KEY,\n" +
                "Prefix VARCHAR(5) NOT NULL,\n" +
                "FirstName VARCHAR(255) NOT NULL,\n" +
                "MiddleInitial VARCHAR(1) NOT NULL,\n"  +
                "LastName VARCHAR(255) NOT NULL,\n"+
                "Gender VARCHAR(1) NOT NULL,\n" +
                "Email VARCHAR(255) NOT NULL,\n" +
                "DateOfBirth DATETIME NOT NULL,\n" +
                "DateOfJoining DATETIME NOT NULL,\n" +
                "Salary(£) INTEGER NOT NULL\n");
        PreparedStatement preparedStatement
                = conn.prepareStatement("INSERT INTO EMPLOYEE(" +
                "ID,Prefix,FirstName, MiddleInitial, LastName," +
                "Gender, Email,DateOfBirth,DateOfJoining,Salary(£)" +
                "VALUES(?, ?, ?, ?, ?, ?,?,?,?,?)");
        for (Employee e: el) {

            preparedStatement.setString(1, String.valueOf(e.getId()));
            preparedStatement.setString(2, e.getPrefix());
            preparedStatement.setString(3, e.getFirst_Name());
            preparedStatement.setString(4,e.getMiddle_initial());
            preparedStatement.setString(5,e.getLast_Name());
            preparedStatement.setString(6,String.valueOf(e.getGender()));
            preparedStatement.setString(7,e.getEmail());
            preparedStatement.setString(8,String.valueOf(e.getDate_Of_Birth()));
            preparedStatement.setString(9,String.valueOf(e.getDate_Of_Joining()));
            preparedStatement.setString(10,String.valueOf(e.getSalary()));
            preparedStatement.execute();

        }
        statement.close();
        conn.close();


    }
    catch(SQLException e){
        e.printStackTrace();

    }
}
}
