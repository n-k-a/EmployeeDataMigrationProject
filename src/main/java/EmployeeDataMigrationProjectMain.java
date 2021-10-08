import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
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
            System.out.println(employeeList.size()+"before");
            HashSet<Employee>  employeeSet = new HashSet<>();
            for (Employee e : employeeList){

                    employeeSet.add(e);


            }
            conflictingEmployeeList= dupeCheck(employeeList,employeeSet);
            employeeList.removeAllElements();
            employeeList.addAll(employeeSet);

            System.out.println(employeeList.size()+"after");
            System.out.println(conflictingEmployeeList.size()+"after for conflict");




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        System.out.println(employeeList.get(0) +"test for working csv pull");

        System.out.println(conflictingEmployeeList.get(0)+ "test for working cleanup with dupe");



    }
    public static Date rebuildDate(String d) throws ParseException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        java.sql.Date returnedDate = java.sql.Date.valueOf(java.time.LocalDate.parse(d,dateFormatter));
        return returnedDate;




    }

    public static void emailCheck(){

    }
    public static Vector<Employee> dupeCheck(Vector<Employee> a, HashSet<Employee> b){
         a.retainAll( b);
         return a;


    }

}

