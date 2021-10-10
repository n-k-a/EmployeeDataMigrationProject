import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.Vector;

public class jdbcDriver {
public void transferToDB(Vector<Employee> el){
    String connection = "jdbc:sqlite:EmployeeRecords.db";
    String sql = "CREATE TABLE Employee("+
            "                ID  integer PRIMARY KEY,\n" +
            "                Prefix text NOT NULL,\n" +
            "                FirstName text NOT NULL,\n" +
            "                MiddleInitial text NOT NULL,\n" +
            "                LastName text NOT NULL,\n" +
            "                Gender text NOT NULL,\n" +
            "                Email text NOT NULL,\n" +
            "                DateOfBirth datetime NOT NULL,\n" +
            "                DateOfJoining datetime NOT NULL,\n" +
            "                Salary(£) integer NOT NULL\n)";
    try(Connection conn = DriverManager.getConnection(connection)){
        Statement statement = conn.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS Employee");

        statement.executeUpdate("CREATE TABLE Employee("+
                "                ID integer primary key not null," +
                "                Prefix text not null," +
                "                FirstName text not null," +
                "                MiddleInitial text not null," +
                "                LastName text not null," +
                "                Gender text not null," +
                "                Email text not null," +
                "                DateOfBirth date not null," +
                "                DateOfJoining date not null," +
                "                Salary integer not null"+");");
        PreparedStatement preparedStatement
                = conn.prepareStatement("INSERT INTO EMPLOYEE(" +
                "ID, Prefix, FirstName, MiddleInitial, LastName," +
                "Gender, Email, DateOfBirth,DateOfJoining, Salary)" +
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
        System.out.println("Success!");

    }
    catch(SQLException e){
        e.printStackTrace();

    }
}
/*public Vector<Employee> returnAllReccords(Vector<Employee> el,){}*/
}
