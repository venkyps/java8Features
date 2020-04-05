package com.learning.designpatterns;

public class PC implements Computer {


    private String ram;

    public PC(String ram){
        this.ram=ram;
    }
    @Override
    public String getRAM() {
        return this.ram;
    }
}
