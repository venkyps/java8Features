package com.learning.defaultandstatic;

public class DefaultAndStatic implements Interface1,Interface2 {

    public static void main(String[] args) {
        DefaultAndStatic defaultAndStatic = new DefaultAndStatic();
        defaultAndStatic.getLocalTime();
        Interface2.getCurrentTimeZone();
        defaultAndStatic.log();
    }

    @Override
    public void log() {
        System.out.println("default and static example");
    }
}
