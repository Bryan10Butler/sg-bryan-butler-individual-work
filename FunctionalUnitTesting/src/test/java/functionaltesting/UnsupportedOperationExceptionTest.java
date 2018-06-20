package functionaltesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnsupportedOperationExceptionTest {

    UnsupportedOperationException unsupported = new UnsupportedOperationException();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void TestFrontTimesReturnTwo() {
        assertEquals("ChoCho", unsupported.frontTimes("Chocolate",2));
    }
    @Test
    public void TestFrontTimesReturnThree() {
        assertEquals("ChoChoCho", unsupported.frontTimes("Chocolate", 3));
    }
    @Test
    public void TestFrontTimesReturnThreeNew() {
        assertEquals("AbcAbcAbc",unsupported.frontTimes("Abc",3));
    }
}