import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class Sign_UPTest {
    Sign_UP s1 = new Sign_UP();
    @Test
    void isAlphaOrSpace() {
        assertTrue(s1.isAlphaOrSpace("Maa kk"));
        assertFalse(s1.isAlphaOrSpace("ss122"));
        assertFalse(s1.isAlphaOrSpace("123"));
        assertFalse(s1.isAlphaOrSpace("J k  "));
        assertFalse(s1.isAlphaOrSpace(" sss oe"));
        assertFalse(s1.isAlphaOrSpace("ddd  sse"));
        assertTrue(s1.isAlphaOrSpace("abcd"));
    }

    @Test
    void isSmaller_valid() {
        assertTrue(s1.isSmaller_valid("01/01/1990"));
        assertFalse(s1.isSmaller_valid("01/01/2100"));
    }

    @Test
    void isbigger_valid() {
        assertTrue(s1.isbigger_valid("01/01/2100"));
        assertFalse(s1.isbigger_valid("01/01/1990"));
    }
}