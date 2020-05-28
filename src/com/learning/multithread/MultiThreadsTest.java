package com.learning.multithread;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class MultiThreadsTest {

    public static void main(String[] args) {
        LocalDateTime start = LocalDateTime.now();
        Info info = new Info();

        //Execution in sequential is very slow.
        /*info.setInfo1(fetchInfo1());
        info.setInfo2(fetchInfo2());
        info.setInfo3(fetchInfo3());*/

        //TODO - Please write your code here ONLY to optimize the program so that we can pass the assertions
        try {
            ExecutorService service = Executors.newFixedThreadPool(3);
            List<Callable<String>> callables = Arrays.asList(
                    () -> fetchInfo1(),
                    () -> fetchInfo2(),
                    () -> fetchInfo3());

            List<Future<String>> stringList = service.invokeAll(callables).stream().collect(Collectors.toList());
            List<String> valueList = new ArrayList<>();

            stringList.forEach(stringFuture -> {
                try {
                    valueList.add(stringFuture.get());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            info.setInfo1(valueList.get(0));
            info.setInfo2(valueList.get(1));
            info.setInfo3(valueList.get(2));
        }catch (Exception e){
            e.printStackTrace();
        }

        LocalDateTime end = LocalDateTime.now();
        Duration duration = Duration.between(start, end);
        System.out.println(duration.getSeconds());
        assertTrue(duration.getSeconds() < 4.1);
        assertEquals("Info1", info.getInfo1());
        assertEquals("Info2", info.getInfo2());
        assertEquals("Info3", info.getInfo3());
        System.exit(0);
    }

    private static String fetchInfo1() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Info1";
    }

    private static String fetchInfo2() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Info2";
    }

    private static String fetchInfo3() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Info3";
    }

    static class Info {
        String info1;
        String info2;
        String info3;

        public String getInfo1() {
            return info1;
        }

        public void setInfo1(String info1) {
            this.info1 = info1;
        }

        public String getInfo2() {
            return info2;
        }

        public void setInfo2(String info2) {
            this.info2 = info2;
        }

        public String getInfo3() {
            return info3;
        }

        public void setInfo3(String info3) {
            this.info3 = info3;
        }
    }

}
