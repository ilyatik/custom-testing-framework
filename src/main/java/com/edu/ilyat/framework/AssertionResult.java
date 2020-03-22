package com.edu.ilyat.framework;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class AssertionResult extends AssertionError {

    private Object asserted;
    private Object expected;
    private Boolean result;
    private String testName;

    protected AssertionResult() {
        this.asserted = null;
        this.expected = null;
        this.result = false;
        this.testName = "";
    }

    protected AssertionResult(Object asserted, Object expected,
                              Boolean result) {
        this.asserted = asserted;
        this.expected = expected;
        this.result = result;
        this.testName = "";
    }

    @Override
    public String toString() {
        return "\nTest name: " + testName +
                "\n\tResult:  " + result.toString() +
                "\n\tExpected: " + expected.toString() +
                "\n\tActual: " + asserted.toString();
    }

}
