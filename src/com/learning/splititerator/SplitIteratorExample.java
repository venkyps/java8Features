package com.learning.splititerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SplitIteratorExample implements Interface3 {

    public static void main(String[] args) {
        List<String> nameList = new ArrayList<>();
        nameList.add("Ram");
        nameList.add("sam");
        nameList.add("arun");

        SplitIteratorExample splitIteratorExample = new SplitIteratorExample();
        splitIteratorExample.tranverse(nameList);

        List<String>  stringList = Stream.generate(() -> new String("Java"))
                .limit(3)
                .collect(Collectors.toList());

        stringList.forEach(val->System.out.println("Value of list "+val));
    }

    @Override
    public void tranverse(List<String> stringList) {

        Spliterator<String> spliterator1= stringList.spliterator();
        Spliterator<String> spliterator2=spliterator1.trySplit();

        System.out.println("characteristics "+spliterator1.characteristics());
        try {
            System.out.println("comparator value " + spliterator1.getComparator());
        }catch(Exception e){
          System.out.println("Not sorted in natural order");
        }

        spliterator1.tryAdvance(
                a -> System.out.println("SplitIterator first result "+a)
        );
        spliterator2.tryAdvance(
                b -> System.out.println("SplitIterator second result "+b)
        );
    }
}
