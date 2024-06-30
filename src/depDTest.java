import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepDTest {
    // this function is re used in a lot of classes.
    @Test
    void testIsNumeric() {
        depD d1 = new depD();
        assertTrue(d1.isNumeric("123456"));
        assertTrue(d1.isNumeric("0"));
        assertFalse(d1.isNumeric("123abc"));
        assertFalse(d1.isNumeric("abc"));
        assertFalse(d1.isNumeric("123 456"));
    }
    @Test
    void testFindAccountByNumber() {
        depD d1 = new depD();
        account a1 = new account("s", "D", new Date(), new Date(), "pass1", "1234567890");
        account a2 =  new account("k", "M", new Date(), new Date(), "pass2", "0987654321");
        List<account> temp = new ArrayList<>();
        temp.add(a1);
        temp.add(a2);
        assertEquals(a1,d1.findAccountByNumber(2023130,temp));
        assertEquals(a2,d1.findAccountByNumber(2023131,temp));
    }
}
