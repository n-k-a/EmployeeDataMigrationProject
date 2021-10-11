import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Vector;
import java.util.stream.Stream;

public class CSVMigrate{

//name for the accepted records
    private String filename;

    public Vector<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(Vector<Employee> employeeList) {
        this.employeeList = employeeList;
    }



    Vector<Employee> employeeList = new Vector<>();
    Vector<Employee> conflictingEmployeeList = new Vector<>();
    Vector<Employee> nullValueEmployeeList = new Vector<>();
    Vector<Employee> failedRegexValueEmployeeList = new Vector<>();
    RegexApplier re = new RegexApplier();
    jdbcDriver driver = new jdbcDriver();

//method to take from CSV
public void transferFromCSV(){
    String line = null;
    String[] data = null;

    //try catch to rip lines from the CSV to be put inot the employee list method
    try (
            BufferedReader in = new BufferedReader(new FileReader("EmployeeRecords.csv"));) {
        //skips the first line(header)
        line = in.readLine();
        //using the ',' as a split for each instance variable
        while ((line = in.readLine()) != null) {
            data = line.split(",");

//adds each value into a new employee object
            employeeList.add(new Employee(Integer.parseInt(data[0]), data[1], data[2], data[3], data[4], data[5].charAt(0),
                    data[6], rebuildDate(data[7]), rebuildDate(data[8]), Integer.parseInt(data[9])));


        }


    } catch (
            FileNotFoundException e) {
        e.printStackTrace();
    } catch (
            IOException e) {
        e.printStackTrace();
    } catch (
            ParseException e) {
        e.printStackTrace();
    }

    System.out.println(employeeList.size() + " Total amount of records within " + filename);


}//Creates a new DB and populates it
public void runPopulateDB(){
    driver.transferToDB(employeeList);

}
//retrieves a DB record who has a matching ID
    public Employee retrieveDB(int id){
        Employee results= driver.returnIDRecord(id);
            System.out.println(results.toString());
            return results;
    }
    //retrieves a DB record who has a matching Last name
    public Vector<Employee> retrieveDB(String LN){
        Vector<Employee> results= driver.returnLNRecords(LN);
        for (Employee e: results){
        System.out.println(e.toString());
        }
        return results;
    }
public void checkRegrex(){
    HashSet<Employee> employeeSet = new HashSet<>();

    for(Employee e: employeeList){
        if(!re.validateEmail(e.getEmail())||!re.validateName(e.getFirst_Name()) ||
                !re.validateName(e.getLast_Name()) ||!re.validateMiddleInitial(e.getMiddle_initial())){
            failedRegexValueEmployeeList.add(e);
        }
        else{
            employeeSet.add(e);

        }
    }
    employeeList.clear();
    employeeList.addAll(employeeSet);
    System.out.println(employeeList.size()+"current size of " +filename);
    System.out.println(failedRegexValueEmployeeList.size()+"Amount of records which did not match text validation");
}
public void checkNull(){
    HashSet<Employee> employeeSet = new HashSet<>();
    for(Employee e: employeeList){
if (e.getFirst_Name().equals(null) || e.getLast_Name().equals(null)||e.getMiddle_initial().equals(null)||e.getEmail().equals(null)
||e.getGender()==' '|| e.getDate_Of_Birth().equals(null)||e.getDate_Of_Joining().equals(null)){
nullValueEmployeeList.add(e);
//employeeList.remove(e);
}
}

    System.out.println(employeeList.size()+"Current size of " + filename);
    System.out.println(nullValueEmployeeList.size()+"Amount of records which failed the null check.");
}

public void checkDuplicates(){
    HashSet<Employee> employeeSet = new HashSet<>();
    for (Employee e : employeeList){

        if(employeeSet.contains(e)){
            conflictingEmployeeList.add(e);
        }
        employeeSet.add(e);


    }
    employeeList.removeAllElements();
    employeeList.addAll(employeeSet);

    System.out.println(employeeList.size()+"Amount of records remaining for " +filename);
    System.out.println(conflictingEmployeeList.size()+" Duplicate records taken");
    employeeSet.clear();

}

    public static Date rebuildDate(String d) throws ParseException {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("M/d/yyyy");

        java.sql.Date returnedDate = java.sql.Date.valueOf(java.time.LocalDate.parse(d,dateFormatter));
        return returnedDate;

    }
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }




}
