package com.learning.collection;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamExample {
    public static void main(String[] args) {
        //streamMap();
        //streamMapReplace();
        //streamMapCompute();
        //streamPutIfAbsent();
        streamMerge();
    }

    private static void streamPutIfAbsent() {


        var words = List.of("Foo", "Bar", "Foo", "Buzz", "Foo", "Buzz", "Fizz", "Fizz");
        var map = new HashMap<String, Integer>();
        words.forEach(word -> {
            map.putIfAbsent(word, 0);
            //map.put(word, map.get(word) + 1);
            map.computeIfPresent(word, (w, prev) -> prev + 1);
        });
        map.forEach((k, v) -> System.out.println("key " + k + " val " + v));
    }

    private static void streamMapCompute() {
        Map<String, String> map = new HashMap<>();
        map.put("Name", "Aman");
        map.put("Address", "Kolkata");
        // Print the map
        System.out.println("Map: " + map);
        // remap the values using compute() method
        map.compute("Name", (key, val) -> val.concat(" Singh"));
        map.compute("Address", (key, val) -> val.concat(" West-Bengal"));
        // print new mapping
        System.out.println("New Map: " + map);
        // compute map using BI function
        map.compute("Name", (key, val)
                -> (key.equals("Name"))
                ? "singh"
                : val + 1);

        System.out.println("BI function Map: " + map);
    }

    private static void streamMapReplace() {
        Map<String, Boolean> booleanMap = new HashMap<String, Boolean>(2);
        booleanMap.put("A", Boolean.TRUE);
        booleanMap.put("B", Boolean.FALSE);
        booleanMap.put("C", Boolean.TRUE);
        System.out.println("Hashmap created, here are the values");
        // Print out the map
        booleanMap.forEach((k, v) -> System.out.println(k + " " + v));
        booleanMap.replaceAll((k, v) -> Boolean.FALSE);
        System.out.println("Hashmap replaceAll, here are the values");
        long startTime = System.currentTimeMillis();
        // Print out the modified values
        booleanMap.forEach((k, v) -> System.out.println(k + " " + v));
        long stopTime = System.currentTimeMillis();
        System.out.println("Replace took time " + (stopTime - startTime));
    }

    private static void streamMap() {
        List<String> numbers = Arrays.asList("1", "2", "3", "4", "5", "6");
        System.out.println("original list: " + numbers);
        List<Integer> even = numbers.stream()
                .map(s -> Integer.valueOf(s))
                .filter(number -> number % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("processed list, only even numbers: " + even);
    }

    private static void streamMerge() {
        var operations = List.of(
                new Operation("123", new BigDecimal("10")),
                new Operation("456", new BigDecimal("1200")),
                new Operation("123", new BigDecimal("-4")),
                new Operation("123", new BigDecimal("8")),
                new Operation("456", new BigDecimal("800")),
                new Operation("456", new BigDecimal("-1500")),
                new Operation("123", new BigDecimal("2")),
                new Operation("123", new BigDecimal("-6.5")),
                new Operation("456", new BigDecimal("-600")),
                new Operation("879", new BigDecimal("-600"))
        );
        var balances = new HashMap<String, BigDecimal>();
        // using computeifpresent
        /*operations.forEach(op -> {
            var key = op.getAccNo();
            balances.putIfAbsent(key, BigDecimal.ZERO);
            balances.computeIfPresent(key, (accNo, prev) -> prev.add(op.getAmount()));
        }); */

        //using merge
        System.out.println("value of map computeifpresent >>" + balances);

     operations.forEach(op ->
             balances.merge(op.getAccNo(), op.getAmount(), (sofar,amount)->sofar.add(amount)));
                     System.out.println("value of map using merge>>"+balances);


    }
}



