package com.edu.ilyat;

import com.edu.ilyat.framework.TestFramework;

public class Application {

    public static void main(String[] args) {
        TestFramework framework = new TestFramework();
        framework.runAllTests("com.edu.ilyat.test");
    }
}
