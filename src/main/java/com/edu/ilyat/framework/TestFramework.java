package com.edu.ilyat.framework;

import com.edu.ilyat.annotation.After;
import com.edu.ilyat.annotation.Before;
import com.edu.ilyat.annotation.Test;
import com.edu.ilyat.util.ReflectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TestFramework {

    Logger logger = LoggerFactory.getLogger(TestFramework.class);

    public void runAllTests(String packageName) {
        List<Class<?>> allClasses = ReflectionUtils.getAllClassesFromPackage(packageName);
        if (allClasses == null || allClasses.size() == 0) {
            logger.error("Failed to read package: {}.", packageName);
            return;
        }

        allClasses.forEach( (iteratedClass) -> {
            List<AssertionResult> results = runTestsFromClass(iteratedClass);
            if (results == null || results.size() == 0) {
                logger.error("Class {} does not contain test methods.", iteratedClass.getName());
                return;
            }
            int passed = (int) results.stream()
                    .filter(AssertionResult::getResult)
                    .count();
            logger.info("\nTest class name: {}\n\tTotal: {}\n\tPassed: {}\n\tFailed: {}", iteratedClass.getName(),
                    results.size(), passed, results.size() - passed);
            results.forEach(result -> logger.info(result.toString()));
        });
    }

    private List<AssertionResult> runTestsFromClass(Class<?> testClass) {
        List<Method> beforeList = getAnnotatedMethods(testClass, Before.class);
        List<Method> afterList = getAnnotatedMethods(testClass, After.class);
        List<Method> testList = getAnnotatedMethods(testClass, Test.class);
        try {
            return getResults(testList, beforeList, afterList, testClass.getConstructor().newInstance());
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException | NoSuchMethodException e) {
            logger.error("Can't create instance of testing class! Error: ", e);
        }
        return null;
    }

    private List<Method> getAnnotatedMethods(Class<?> testClass, Class clazz) {
        return (List<Method>) Arrays.stream(testClass.getMethods())
                .filter(method -> method.getAnnotation(clazz) != null)
                .collect(Collectors.toList());
    }

    private List<AssertionResult> getResults(List<Method> tests, List<Method> beforeTest,
                                             List<Method> afterTest, Object testCassInstance) {

        return tests.stream()
                .map(test -> runSingleTest(test, beforeTest, afterTest, testCassInstance))
                .collect(Collectors.toList());
    }

    private AssertionResult runSingleTest(Method test, List<Method> beforeList, List<Method> afterList, Object testClass) {
        AssertionResult testResult = new AssertionResult();
        beforeList.forEach(method ->
                invoke(method, testClass));
        try {
            test.invoke(testClass);
        } catch (InvocationTargetException e) {
            if (e.getTargetException() instanceof AssertionResult) {
                testResult = (AssertionResult) e.getTargetException();
            }
        } catch (IllegalAccessException e) {
            logger.error("Failed to call method! Error: ", e);
        }
        afterList.forEach(method ->
                invoke(method, testClass));
        testResult.setTestName(test.getName());
        return testResult;
    }

    private void invoke(Method method, Object testClass) {
        try {
            method.invoke(testClass);
        } catch (IllegalAccessException | InvocationTargetException e) {
            logger.error("Failed to call method! Error: ", e);
        }
    }
}
