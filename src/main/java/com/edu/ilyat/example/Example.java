package com.edu.ilyat.example;

import lombok.Getter;

@Getter
public class Example {

    private boolean isTrue;
    private boolean isFalse;
    private Object notNullObject;
    private int threePlusFive;
    private int elevenMinusEight;
    private int threeDivTwo;
    private int tenModFive;
    private boolean trueIfPassedThroughAllTest;

    public Example() {
        this.isTrue = true;
        this.isFalse = false;
        this.notNullObject = new Object();
        this.threePlusFive = 8;
        this.elevenMinusEight = 3;
        this.threeDivTwo = 1;
        this.tenModFive = 0;
    }

    public void setTrueIfPassedThroughAllTest(boolean trueIfPassedThroughAllTest) {
        this.trueIfPassedThroughAllTest = trueIfPassedThroughAllTest;
    }
}
