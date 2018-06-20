package functionaltesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GreatPartyTest {

    public GreatPartyTest() {

    }

    GreatParty party = new GreatParty();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test30False() {

        //assertFalse - expect false to come back
        assertFalse(party.greatParty(30,false));
    }

    @Test
    public void test50False() {
        //assertTrue - expect true to come back
        assertTrue(party.greatParty(50,false));
    }

    @Test
    public void test70True(){
        //assertTrue - expect True to come back
        assertTrue(party.greatParty(70,true));

    }


}