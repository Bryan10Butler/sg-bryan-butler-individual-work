package com.sg;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExampleTest {

    Example example = new Example();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getNumber() {

        int numberReturned = example.getNumber();
        assertEquals(1, numberReturned );
    }
}