import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {

    @Test
    void testFindMatchingAccountNumber() {
        account a1 = new account("s", "D", new Date(), new Date(), "pass1", "1234567890");
        account a2 =  new account("k", "M", new Date(), new Date(), "pass2", "0987654321");
        List<account> temp = new ArrayList<>();
        temp.add(a1);
        temp.add(a2);
        Login login = new Login();

        int foundAccNum = login.findMatchingAccountNumber(2023130, "pass1", temp);
        assertEquals(2023130, foundAccNum);

        int notFoundDueToPassword = login.findMatchingAccountNumber(2023130, "wrongpass", temp);
        assertEquals(-1, notFoundDueToPassword);

        int notFoundDueToAccNum = login.findMatchingAccountNumber(2023155, "pass1", temp);
        assertEquals(-1, notFoundDueToAccNum);
    }
}
