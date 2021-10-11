import java.util.Scanner;

public class EmployeeDataMigrationProjectMain {
    public static void main(String[] args) {

         CSVHandler h1 = new CSVHandler();
         h1.setRunnableID(1);
        CSVHandler h2 = new CSVHandler();
        h2.setRunnableID(2);

        Thread t1 = new Thread(h1);
        Thread t2 = new Thread(h2);
        t1.start();
        t2.start();
        try{
            t1.join();
            t2.join();

        }
        catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println("Threads done");

    }



}

