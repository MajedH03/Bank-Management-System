import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class listTest {

    @BeforeEach
    void setUp() {
        String accountT = "accountstest.ser";
        String counterT = "countertest.txt";
        list.initialize(accountT, counterT);
    }
    // i do storing and then loading to check that both functions work
    @Test
    @Order(1)
    void testStoreListAndCounter() {
        list.getInstance().add(new account("abc", "Der", new Date(), new Date(), "pass123", "1234567890"));

        list.storelistandcounter();

    }

    @Test
    @Order(2)
    void testLoadListAndCounter() {
        list.loadlistandcounter();

        List<account> loadedAccounts = list.getInstance();
        assertNotNull(loadedAccounts);
        assertFalse(loadedAccounts.isEmpty());

    }

}