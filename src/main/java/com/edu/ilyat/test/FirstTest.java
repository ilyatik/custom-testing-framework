package com.edu.ilyat.test;

import com.edu.ilyat.annotation.After;
import com.edu.ilyat.annotation.Before;
import com.edu.ilyat.annotation.Test;
import com.edu.ilyat.example.Example;
import com.edu.ilyat.framework.Asserts;

import static com.edu.ilyat.framework.Asserts.assertNotNull;
import static com.edu.ilyat.framework.Asserts.assertTrue;

public class FirstTest {

    private Example example;

    @Before
    public void setup() { example = new Example(); }

    @Test
    public void TestIsTrue() {
        assertTrue(example.isTrue());
    }

    @Test
    public void TestIsFalse() {
        assertTrue(!example.isFalse());
    }

    @Test
    public void TestNullObject() {
        assertNotNull(example.getNotNullObject());
    }

    @After
    public void clean() { example.setTrueIfPassedThroughAllTest(true); }
}
