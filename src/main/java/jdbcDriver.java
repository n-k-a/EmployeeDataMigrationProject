import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Vector;

public class jdbcDriver {
    private Connection conn;
private String connection = "jdbc:sqlite:EmployeeRecords.db";
    private static Logger logger = Logger.getLogger("My Application Logger");

    public void connectToDB(){
        PropertyConfigurator.configure("log4j.properties");

        try{
            conn = DriverManager.getConnection(connection);
        }
        catch(SQLException e){
            logger.trace(e);
            e.printStackTrace();

        }
    }
public  synchronized void transferToDB(Vector<Employee> el){
    try{
        connectToDB();
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
            preparedStatement.addBatch();
        }
        preparedStatement.executeBatch();

        statement.close();
        conn.close();
        System.out.println("Success!");

    }
    catch(SQLException e){
        e.printStackTrace();

    }
}
//looks for employees with the last name of that given param
    //returns a list as Names are less unique than the PK.
    public synchronized Vector<Employee> returnLNRecords(String LN){
        Vector<Employee> resultEmployee = new Vector<>();
        String connection = "jdbc:sqlite:EmployeeRecords.db";
        String query = "SELECT * FROM Employee WHERE LastName = ?";
        try {
            connectToDB();
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setString(1,LN);
            ResultSet result = preparedStatement.executeQuery();
            while (result.next()){
                //properly formats the date so it fits with the Employee's version
                DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                Date date = java.sql.Date.valueOf(java.time.LocalDate.parse(result.getString("DateOfBirth"),dateFormatter));
                Date date2 = java.sql.Date.valueOf(java.time.LocalDate.parse(result.getString("DateOfJoining"),dateFormatter));
                //adds each record's cells into a new employee object
                resultEmployee.add(new Employee(result.getInt("ID"),
                        result.getString("Prefix"),
                        result.getString("FirstName"),
                        result.getString("MiddleInitial"),
                        result.getString("LastName"),
                        result.getString("Gender").charAt(0),
                        result.getString("Email"),
                        date,
                        date2,
                        result.getInt("Salary")));
            }
        }
        catch(SQLException e){
            logger.trace(e);
            e.printStackTrace();

        }
        //returns employee
        return resultEmployee;
    }
public synchronized Employee returnIDRecord(int id){
    Employee resultEmployee = new Employee();
    String connection = "jdbc:sqlite:EmployeeRecords.db";
    String query = "SELECT * FROM Employee WHERE ID = ?";
    try {
        connectToDB();
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setInt(1,id);
        ResultSet result = preparedStatement.executeQuery();
        while (result.next()){
            //properly formats the date so it fits with the Employee's version
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            Date date = java.sql.Date.valueOf(java.time.LocalDate.parse(result.getString("DateOfBirth"),dateFormatter));
            Date date2 = java.sql.Date.valueOf(java.time.LocalDate.parse(result.getString("DateOfJoining"),dateFormatter));
            //adds each record's cells into a new employee object
            resultEmployee = (new Employee(result.getInt("ID"),
                    result.getString("Prefix"),
                    result.getString("FirstName"),
                    result.getString("MiddleInitial"),
                    result.getString("LastName"),
                    result.getString("Gender").charAt(0),
                    result.getString("Email"),
                    date,
                    date2,
                    result.getInt("Salary")));
        }
    }
    catch(SQLException e){
        logger.trace(e);
        e.printStackTrace();

    }
    //returns employee
    return resultEmployee;
    }



//version of transferToDB but without the drop db method
    public synchronized void addEmployeeListToDB(Vector<Employee> el){
        try{
            connectToDB();
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
                preparedStatement.addBatch();

            }
            preparedStatement.executeBatch();
            preparedStatement.close();
            conn.close();
            System.out.println("Success!");

        }
        catch(SQLException e){
            logger.trace(e);
            e.printStackTrace();

        }
    }

}
