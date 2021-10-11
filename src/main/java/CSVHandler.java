import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class CSVHandler implements Runnable{
    private int runnableID;
    private EmployeeRetrieval employeeRetrieval = new EmployeeRetrieval();




    private void setFileNames(){
    if (runnableID == 1){
        employeeRetrieval.setFilename("EmployeeRecords");
    }
    else if (runnableID ==2) {
        employeeRetrieval.setFilename("EmployeeRecordsLarge") ;
    }
}
public void runCSV(){
    setFileNames();
    employeeRetrieval.transferFromCSV();
    try {
        Thread.sleep(new Random().nextInt(5) * 1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    if (runnableID == 1) {

        employeeRetrieval.checkNull();
        employeeRetrieval.checkRegrex();
        employeeRetrieval.checkDuplicates();
        try {
            Thread.sleep(new Random().nextInt(5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }


}

public synchronized void runDBInsert(){
    Scanner scan1 = new Scanner(System.in);
    System.out.println("Do you want to recreate a Table to repopulate, insert these files into the current " +
            "database using " + employeeRetrieval.getFilename() +"? Please type: \n 'DROP' \n 'INSERT'\n 'EXIT'");
    String choice = scan1.nextLine().toUpperCase();
    while (true){
        if (choice.equals("DROP")){
            employeeRetrieval.runPopulateDB();
            System.out.println("Dropping and rebuilding DB.");
            break;
        }
         if (choice.equals("INSERT")){
            employeeRetrieval.runInsertIntoDB();
            System.out.println("Inserting into DB.");
            break;

        }
         if (choice.equals("EXIT")){
            System.out.println("exiting.");

            break;
        }
        else{
            System.out.println("please enter a proper command.");
             choice = scan1.nextLine().toUpperCase();

        }
        try {
            Thread.sleep(new Random().nextInt(5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}

public  void runDBQueries() {
    Scanner scan = new Scanner(System.in);
    System.out.println("Do you want to search for Queries with the criteria of 'ID' or 'Lastname'?");
    System.out.println("Please type either \n'ID' or \n'LASTNAME' or \n 'EXIT'");
    String choice = scan.nextLine().toUpperCase();
    while (true) {
        if (choice.equals("ID")) {
            System.out.println("Please enter the ID you want to search for");
            int id = scan.nextInt();
            try {
                employeeRetrieval.retrieveDBID(id);
                System.out.println("Files for " + employeeRetrieval.getFilename() +" Transfered");
            }
            catch (InputMismatchException e){
                e.printStackTrace();
                System.out.println("Please input a proper ID value.");
            }
            break;
        } if (choice.equals("LASTNAME")) {
            System.out.println("Please enter the ID you want to search for");
            String lastName = scan.next();
            employeeRetrieval.retrieveDBLastName(lastName);
            break;

        }  if (choice.equals("EXIT")) {
            System.out.println("exiting.");
            break;
        } else {
            System.out.println("please enter a proper command.");
             choice = scan.nextLine().toUpperCase();
        }

        try {
            Thread.sleep(new Random().nextInt(5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }   }
}
    @Override
    public void run() {
    runCSV();
        runDBInsert();

        runDBQueries();
    }
    public int getRunnableID() {
        return runnableID;
    }

    public void setRunnableID(int runnableID) {
        this.runnableID = runnableID;
    }

}
