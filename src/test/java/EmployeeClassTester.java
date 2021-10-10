import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class EmployeeClassTester {
    //testing whether assert equals work in general.
    @Test
    public void checkEmpEquals(){
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        Employee e1 = new Employee(1234,"Mr.","Naruhodo", "P", "Ryonusuke",'n', "RNaruhodo@rmail.jp",sqlDate, sqlDate, 10000 );
        Employee e2 = new Employee(1234,"Mr.","Naruhodo", "P", "Ryonusuke",'n', "RNaruhodo@rmail.jp",sqlDate, sqlDate, 10000 );
        assertTrue(e1.equals(e2));

    }
    //checking if a different ID despite the same data makes it so that it's not the same record.
    @Test
    public void checkEmpIDNotEquals(){
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        Employee e1 = new Employee(1234,"Mr.","Naruhodo", "P", "Ryonusuke",'n', "RNaruhodo@rmail.jp",sqlDate, sqlDate, 10000 );
        Employee e2 = new Employee(1233,"Mr.","Naruhodo", "P", "Ryonusuke",'n', "RNaruhodo@rmail.jp",sqlDate, sqlDate, 10000 );
        assertFalse(e1.equals(e2));

    }
    //test to see if the same ID still forces an assert equals
    @Test
    public void checkEmpIDEquals(){
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        Employee e1 = new Employee(1234,"Mr.","Jack", "C", "Garland",'n', "jgarland@cmail.jp",new Date(1220227200L * 1000), new Date(1220227200L * 1000), 10000 );
        Employee e2 = new Employee(1234,"Mr.","Naruhodo", "P", "Ryonusuke",'n', "RNaruhodo@rmail.jp",sqlDate, sqlDate, 10000 );
        assertTrue(e1.equals(e2));

    }

    @Test
    public void checkRegexEmail(){
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        Employee e1 = new Employee(1234,"Mrs.","Danae", "P", "Batchelder",'F', "danae.batchelder@msn.com",sqlDate, sqlDate, 10000 );
        RegexApplier re = new RegexApplier();
        assertTrue(re.validateEmail(e1.getEmail()));
        assertFalse(re.validateEmail("Nardsssdfdsdsddwsdsd.nnnn"));


    }
    @Test
    public void checkRegexName(){
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        Employee e1 = new Employee(1234,"Mrs.","Danae", "P", "Batchelder",'F', "danae.batchelder@msn.com",sqlDate, sqlDate, 10000 );
        RegexApplier re = new RegexApplier();
        assertTrue(re.validateName(e1.getFirst_Name()));
        assertFalse(re.validateName("3344ffss"));
        assertFalse(re.validateName("....."));

    }
    @Test
    public void checkRegexInitial(){
        long now = System.currentTimeMillis();
        Date sqlDate = new Date(now);
        Employee e1 = new Employee(1234,"Mrs.","Danae", "P", "Batchelder",'F', "danae.batchelder@msn.com",sqlDate, sqlDate, 10000 );
        Employee e2 = new Employee(1234,"Mrs.","Danae", "PN", "Batchelder",'F', "danae.batchelder@msn.com",sqlDate, sqlDate, 10000 );
        Employee e3 = new Employee(1234,"Mrs.","Danae", "6", "Batchelder",'F', "danae.batchelder@msn.com",sqlDate, sqlDate, 10000 );

        RegexApplier re = new RegexApplier();
        assertTrue(re.validateMiddleInitial(e1.getMiddle_initial()));
        assertFalse(re.validateMiddleInitial(e3.getMiddle_initial()));

    }
}
