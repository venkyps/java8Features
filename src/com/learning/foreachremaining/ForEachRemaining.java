package com.learning.foreachremaining;

import java.util.ArrayList;
import java.util.List;

public class ForEachRemaining {
    public static void main(String[] args) {
        List<String> nameList = new ArrayList<>();
        nameList.add("abc");
        nameList.add("def");
        nameList.spliterator().forEachRemaining(val->System.out.println(val));
    }
}
