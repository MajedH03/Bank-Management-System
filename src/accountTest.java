import org.junit.jupiter.api.Test;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class accountTest {

    @Test
    void testAccountConstructorAndAllGetters() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2020, Calendar.JANUARY, 1);
        Date dateOfCreation = calendar.getTime();
        calendar.set(1990, Calendar.DECEMBER, 31);
        Date dateOfBirth = calendar.getTime();
        String testName = "Majed";
        String testJob = "OK";
        String testPass = "pass";
        String testPnum = "1234";

        account testAccount = new account(testName, testJob, dateOfCreation, dateOfBirth, testPass, testPnum);

        assertEquals(testName, testAccount.getName());
        assertEquals(testJob, testAccount.getJob());
        assertEquals(dateOfCreation, testAccount.getDateofc());
        assertEquals(dateOfBirth, testAccount.getDateofb());
        assertEquals(testPnum, testAccount.getPnum());
        assertEquals(0.0, testAccount.getBalance());
    }

    @Test
    void testSetters() {
        account testAccount = new account();
        String newName = "Majed";
        String newJob = "OK";
        String newPnum = "5678";

        testAccount.setName(newName);
        testAccount.setJob(newJob);
        testAccount.setpnum(newPnum);

        assertEquals(newName, testAccount.getName());
        assertEquals(newJob, testAccount.getJob());
        assertEquals(newPnum, testAccount.getPnum());
    }

    @Test // this is used for withdraw, deposit, transaction
    void changeinbalance(){
        account testAccount = new account();
        testAccount.SetBalance(100);
        testAccount.changeinBalance(-50);
        assertEquals(50.0,testAccount.getBalance());
    }
}
