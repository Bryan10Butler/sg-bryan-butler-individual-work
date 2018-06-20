package functionaltesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class canHazTableTest {

    canHazTable table = new canHazTable();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    // canHazTable(5, 10) → 2
    public void testCanHazTableResult2() {
        assertEquals(2, table.canHazTable(5,10));
    }

    @Test
    // canHazTable(5, 2) → 0
    public void testCanHazTableResult0() {
        assertEquals(0,table.canHazTable(5,2));
    }

    @Test
    // canHazTable(5, 5) → 1
    public void testCanHazTableResult1(){
        assertEquals(1, table.canHazTable(5,5));
    }
}