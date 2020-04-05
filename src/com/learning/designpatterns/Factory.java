package com.learning.designpatterns;

public class Factory {

    public static Computer getComputer(String type, String ram){
        if("PC".equalsIgnoreCase(type)) return new PC(ram);
        return null;
    }
}
