import java.sql.Date;
import java.util.Objects;

public class Employee implements Comparable<Employee> {
    private int id;
    private String prefix;
    private String first_Name;
    private String last_Name;
    private String middle_initial;
    private char gender;
    private String email;
    private Date date_Of_Birth;
    private Date date_Of_Joining;
    private int salary;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getFirst_Name() {
        return first_Name;
    }

    public void setFirst_Name(String first_Name) {
        this.first_Name = first_Name;
    }

    public String getMiddle_initial() {
        return middle_initial;
    }

    public void setMiddle_initial(String middle_initial) {
        this.middle_initial = middle_initial;
    }

    public String getLast_Name() {
        return last_Name;
    }

    public void setLast_Name(String last_Name) {
        this.last_Name = last_Name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate_Of_Birth() {
        return date_Of_Birth;
    }

    public void setDate_Of_Birth(Date date_Of_Birth) {
        this.date_Of_Birth = date_Of_Birth;
    }

    public Date getDate_Of_Joining() {
        return date_Of_Joining;
    }

    public void setDate_Of_Joining(Date date_Of_Joining) {
        this.date_Of_Joining = date_Of_Joining;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Employee(int id, String prefix, String first_Name, String middle_initial,String last_Name, char gender, String email, Date date_Of_Birth, Date date_Of_Joining, int salary) {
        this.id = id;
        this.prefix = prefix;
        this.first_Name = first_Name;
        this.middle_initial = middle_initial;
        this.last_Name = last_Name;
        this.gender = gender;
        this.email = email;
        this.date_Of_Birth = date_Of_Birth;
        this.date_Of_Joining = date_Of_Joining;
        this.salary = salary;
    }
    public Employee(){

    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", prefix='" + prefix + '\'' +
                ", first_Name='" + first_Name + '\'' +
                ", middle_initial='" + middle_initial + '\'' +
                ", last_Name='" + last_Name + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                ", date_Of_Birth=" + date_Of_Birth +
                ", date_Of_Joining=" + date_Of_Joining +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        o = (Employee)o;
        if (this== o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Employee o) {
        return this.compareTo(o);

    }
}
