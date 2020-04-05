package com.learning.designpatterns;

public class TestDesignPatterns {

    public static void main(String[] args) {
        testAbstractFactory();
    }

    private static void testAbstractFactory() {
        Computer pc = com.learning.designpatterns.ComputerFactory.getComputer(new PCFactory("2 GB"));
        System.out.println("AbstractFactory PC Config::"+pc);
    }
}
