package com.edu.ilyat.test.inner;

import com.edu.ilyat.annotation.After;
import com.edu.ilyat.annotation.Before;
import com.edu.ilyat.annotation.Test;
import com.edu.ilyat.example.Example;
import com.edu.ilyat.framework.Asserts;

import static com.edu.ilyat.framework.Asserts.assertEquals;
import static com.edu.ilyat.framework.Asserts.assertNotNull;

public class SecondTest {

    private Example example;

    @Before
    public void setup() {
        example = new Example();
    }

    @Test
    public void TestThreePlusFive() {
        assertEquals(example.getThreePlusFive(), 8);
    }

    @Test
    public void TestElevenMinusEight() {
        assertEquals(example.getElevenMinusEight(), 4);
    }

    @Test
    public void TestThreeDivTwo() {
        assertEquals(example.getThreeDivTwo(), 1);
    }

    @Test
    public void TestTenModFive() {
        assertEquals(example.getTenModFive(), 2);
    }

    @After
    public void clean() { example.setTrueIfPassedThroughAllTest(true); }
}
