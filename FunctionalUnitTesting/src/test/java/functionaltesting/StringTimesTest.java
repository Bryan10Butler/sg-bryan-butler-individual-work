package functionaltesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StringTimesTest {

    //Create instance of StringTimes
    StringTimes stringTime = new StringTimes();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    // stringTimes("Hi", 2) -> "HiHi"
    public void testHiHiStringTimes() {
        assertEquals("HiHi", stringTime.stringTimes("Hi", 2));
    }

    @Test
    // stringTimes("Hi", 3) -> "HiHiHi"
    public void testHiHiHiStringTime() {
        assertEquals("HiHiHi", stringTime.stringTimes("Hi",3));
    }

    @Test
    // stringTimes("Hi", 1) -> "Hi"
    public void testHi() {
        assertEquals("Hi", stringTime.stringTimes("Hi",1));
    }
}