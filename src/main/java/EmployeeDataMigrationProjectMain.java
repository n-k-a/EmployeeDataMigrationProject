import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class EmployeeDataMigrationProjectMain {
    public static void main(String[] args) {
        Vector<Employee> employeeList = new Vector<>();
        Vector<Employee> conflictingEmployeeList = new Vector<>();

        String line = null;
        String[] data = null;

        try (BufferedReader in = new BufferedReader(new FileReader("EmployeeRecords.csv"));) {
            line = in.readLine();
            while ((line = in.readLine()) != null) {
                data = line.split(",");


                employeeList.add(new Employee(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5].charAt(0),
                        data[6], rebuildDate(data[7]), rebuildDate(data[8]), Integer.parseInt(data[9])));
                //   System.out.println(line);}

            }
            conflictingEmployeeList = dupeCheck(employeeList);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //System.out.println(employeeList.get(1));

        //System.out.println(conflictingEmployeeList.get(0));



    }
    public static Date rebuildDate(String d) throws ParseException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        java.sql.Date returnedDate = java.sql.Date.valueOf(java.time.LocalDate.parse(d,dateFormatter));
        return returnedDate;




    }
    public static Vector<Employee>  dupeCheck(Vector<Employee> arrlist){
        Vector<Employee> conflictingEmployeeList = new Vector<>();
        Set<Employee>  employeeSet = new HashSet<>();
        for (Employee e : arrlist){
            if (employeeSet.add(e) == false)
            {
                conflictingEmployeeList.add(e);
                System.out.println(e);
            }
        }

return conflictingEmployeeList;
    }

}

