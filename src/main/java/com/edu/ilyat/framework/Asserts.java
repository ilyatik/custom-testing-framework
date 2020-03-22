package com.edu.ilyat.framework;

public class Asserts {

    public static void assertTrue(Object obj) {
        if (!obj.equals(true)) {
            throw new AssertionResult(obj, true, false);
        } else {
            throw new AssertionResult(obj, true, true);
        }
    }

    public static void assertEquals(Object left, Object right) {
        if (!left.equals(right)) {
            throw new AssertionResult(left, right, false);
        } else {
            throw new AssertionResult(left, right, true);
        }
    }

    public static void assertNotNull(Object obj) {
        if (obj == null) {
            throw new AssertionResult(null, "not null object", false);
        } else {
            throw new AssertionResult(obj, "not null object", true);
        }
    }
}
