package functionaltesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MischeviousChildrenTest {

    MischeviousChildren childrenTest = new MischeviousChildren();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    // areWeInTrouble(true, true) -> true
    public void testAreWeInTroubleTrueTrue() {
        assertTrue(childrenTest.areWeInTrouble(true,true));
    }

    // areWeInTrouble(false, false) -> true
    @Test
    public void testAreWeInTroubleFalseFalse() {
        assertTrue(childrenTest.areWeInTrouble(false,false));
    }

    // areWeInTrouble(true, false) -> false
    @Test
    public void testAreWeInTroubleTrueFalse() {
        assertFalse(childrenTest.areWeInTrouble(true, false));
    }
}