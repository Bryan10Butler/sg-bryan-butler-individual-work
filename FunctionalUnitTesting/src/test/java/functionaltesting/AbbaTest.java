package functionaltesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AbbaTest {

    Abba ab = new Abba();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    // abba("Hi", "Bye") -> "HiByeByeHi"
    public void abba() {
        assertEquals("HiByeByeHi", ab.abba("Hi","Bye"));
    }

    @Test
    // abba("Yo", "Alice") -> "YoAliceAliceYo"
    public void abbaTestTwo() {
        assertEquals("YoAliceAliceYo", ab.abba("Yo" ,"Alice"));
    }

    @Test
    // abba("What", "Up") -> "WhatUpUpWhat"
    public void abbaTestThree() {
        assertEquals("WhatUpUpWhat", ab.abba("What", "Up"));
    }
}