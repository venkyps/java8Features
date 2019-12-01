package com.learning.foreach;

import java.util.*;
import java.util.function.Consumer;

public class Java8ForEachExample {

    public static void main(String[] args) {

        //creating sample Collection
        List<Employee> employeeList = new ArrayList<Employee>();
        for(int i=0; i<3; i++){
            Employee employee = new Employee();
            employee.setEmpID(String.valueOf(i));
            employee.setEmpName("Test"+i);
            employeeList.add(employee);
        }

        //traversing through forEach method of Iterable with anonymous class
        employeeList.forEach(new Consumer<Employee>() {
            public void accept(Employee employee) {
                System.out.println("forEach anonymous class Value::"+employee);
            }
        });

        //traversing with Consumer interface implementation
        MyConsumer action = new MyConsumer();
        employeeList.forEach(action);

        Map<String,String> countryCapitalMap = new HashMap<>();
        countryCapitalMap.put("US", "Wshington DC");
        countryCapitalMap.put("England", "London");
        countryCapitalMap.put("France", "Paris");
        //Foreach in map
        Java8ForEachExample.iterateThroughMap(countryCapitalMap);

        // Foreach in stream
        List<String> countryList = Arrays.asList("Argentina", "Brasil", "China", "United States");
        Java8ForEachExample.iterateThroughListStream(countryList);


    }

    /**
     * Foreach in stream API
     * @param list
     */
    public static void iterateThroughListStream(List<String> list){
        list.stream().forEach(System.out::println);
    }

    /**
     * Foreach in map
     * @param map
     */
    public static void iterateThroughMap(Map<?,?> map){
        map.forEach((k,v) -> {System.out.println("Key: "+k+" Value: "+v);});
    }

}

//Consumer implementation that can be reused
class MyConsumer implements Consumer<Employee>{
    public void accept(Employee employee) {
        System.out.println("Consumer impl Value::"+employee);
    }
}

