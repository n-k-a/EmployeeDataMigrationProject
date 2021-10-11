import java.util.Random;

public class CSVHandler implements Runnable{
    private int runnableID;
    private CSVMigrate csvMigrate = new CSVMigrate();


private void setFileNames(){
    if (runnableID == 1){
        csvMigrate.setFilename("EmployeeRecords");
    }
    else if (runnableID ==2) {
        csvMigrate.setFilename("EmployeeRecordsLarge") ;
    }
}
public void runCSV(){
    setFileNames();
    csvMigrate.transferFromCSV();
    try {
        Thread.sleep(new Random().nextInt(5) * 1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }

    if (runnableID == 1) {

        csvMigrate.checkNull();
        csvMigrate.checkRegrex();
        csvMigrate.checkDuplicates();
        try {
            Thread.sleep(new Random().nextInt(5) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
public void runDBTasks(){

}
    @Override
    public void run() {
    runCSV();
    }
    public int getRunnableID() {
        return runnableID;
    }

    public void setRunnableID(int runnableID) {
        this.runnableID = runnableID;
    }

}
