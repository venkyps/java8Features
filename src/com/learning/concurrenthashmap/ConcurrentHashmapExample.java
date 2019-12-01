package com.learning.concurrenthashmap;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashmapExample {
    public static void main(String[] args) {
        ConcurrentHashMap<String, Integer> hashMap = new ConcurrentHashMap<>();
        hashMap.put("A", 1);
        hashMap.put("B", 2);
        hashMap.put("F", 6);
        hashMap.put("G", 7);
        hashMap.put("C", 3);
        hashMap.put("D", 4);
        hashMap.put("E", 5);

        hashMap.forEach(2, (k, v) -> System.out.println("key->" + k + "is related with value-> " + v +", by thread-> "+ Thread.currentThread().getName()));

        String result = hashMap.search(1, (k, v) -> {
            System.out.println(Thread.currentThread().getName());
            if (k.equals("A"))
                return k +"-" +v;
            return null;
        });
        System.out.println("result => " +result);
        System.out.println("map values "+hashMap);
        System.out.println("get the value of the key not present" +hashMap.getOrDefault("E1", 115));

        hashMap.reduceKeys(1, (k1, k2) -> k1.compareTo(k2) < 0 ? k1 : k2);
        System.out.println("reduce keys :: "+hashMap);
        //hashMap.reduceValues(1, List::size, (total, elem) -> total + elem);

        hashMap.entrySet().stream().forEach(e->System.out.println("key >>"+e.getKey() +" value >>"+e.getValue()));
    }
}
