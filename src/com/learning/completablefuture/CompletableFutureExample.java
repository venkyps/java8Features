package com.learning.completablefuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CompletableFutureExample {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        //runAsyncMethod();
        //completeFutureMethod();
        //completeFuture();
        //cancellationException();
        CompletableFuture<String> completableFuture = supplyAsync();
        System.out.println("supplyAsync >> "+completableFuture.get());
        CompletableFuture<String> thenComposeMethod =  thenComposeMethod();
        System.out.println("thenComposeMethod >> "+thenComposeMethod.get());
        CompletableFuture<String> thenCombineMethod =  thenCombineMethod();
        System.out.println("thenCombineMethod >> "+thenCombineMethod.get());
        thenAcceptBothMethod();
        CompletableFuture<Integer> finalResult = compute().thenApply(s-> s + 1);
        System.out.println("finalResult >>"+finalResult.get());
        CompletableFuture<Integer> finalResultThenCompose = compute().thenCompose(i->computeAnother(i));
        System.out.println("finalResultThenCompose >>"+finalResultThenCompose.get());
        CompletableFuture<Void> combinedFuture= getCombinedFuture();
        System.out.println("combined future >>"+combinedFuture.get());
        CompletableFuture<String> completableFutureException= completableFutureExceptionHandle();
        System.out.println("completable exception >> "+completableFutureException.get());
        CompletableFuture<String> thenApplyAsync= thenApplyAsync();
        System.out.println("thenApplyAsync >> "+thenApplyAsync.get());

    }

    private static CompletableFuture<String> thenApplyAsync(){
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> future = completableFuture
                .thenApplyAsync(s -> s + " World");

        return  future;
    }

    private static CompletableFuture<String> completableFutureExceptionHandle() throws ExecutionException {
        String name=null;
        CompletableFuture<String> completableFuture
                =  CompletableFuture.supplyAsync(() -> {
            if (name == null) {
                throw new RuntimeException("Computation error!");
            }
            return "Hello, " + name;
        }).handle((s, t) -> s != null ? s : t.getMessage());

        CompletableFuture<String> completableFutureExc = new CompletableFuture<>();
        completableFutureExc.completeExceptionally(
                new RuntimeException("Calculation failed!"));
        //try {
            System.out.println("Exec in completable future"+completableFutureExc);
        /*}catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        return completableFuture;
    }

    private static CompletableFuture<Void> getCombinedFuture(){
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Hello");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Beautiful");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "World");

        CompletableFuture<Void> combinedFuture
                = CompletableFuture.allOf(future1, future2, future3);

        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));

        System.out.println("Combined result using stream "+combined);

        return  combinedFuture;
    }
    private static  CompletableFuture<Integer> computeAnother(Integer i){
        return CompletableFuture.supplyAsync(() -> 11 + i);
    }

    private static CompletableFuture<Integer>  compute(){
        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> 10);
        return completableFuture;

    }

    private static void thenAcceptBothMethod(){

        CompletableFuture future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenAcceptBoth(CompletableFuture.supplyAsync(() -> " World"),
                        (s1, s2) -> System.out.println(s1 + s2));
    }

    private static CompletableFuture<String> thenCombineMethod(){
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCombine(CompletableFuture.supplyAsync(
                        () -> " World"), (s1, s2) -> s1 + s2);

        return completableFuture;
    }

    private static CompletableFuture<String> thenComposeMethod(){
        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " World"));
        return  completableFuture;
    }
    private static CompletableFuture<String> supplyAsync(){

        CompletableFuture<String> completableFuture
                = CompletableFuture.supplyAsync(() -> "Hello");

        CompletableFuture<String> future = completableFuture
                .thenApply(s -> s + " World");

        CompletableFuture<Void> futureWithoutReturn = completableFuture
                .thenAccept(s -> System.out.println("Computation returned: " + s));

        CompletableFuture<Void> futureWithRun = completableFuture
                .thenRun(() -> System.out.println("Computation finished."));

        return future;
    }
    private static void completeFuture() throws InterruptedException {
        Future<Employee> future = calculateAsync();
        try {
            if(future.isDone()){
                System.out.println("value of complete future "+future.get());
            }else {
                System.out.println("complete future not done "+future.get().getEmpID());
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void cancellationException() throws InterruptedException {
        try{
            Future<String> futureCancel = calculateAsyncWithCancellation();
            futureCancel.get(); // CancellationException
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void completeFutureMethod() {
        CompletableFuture<String> cfs = CompletableFuture.completedFuture("message");
        System.out.println("is done for cfs "+cfs.getNow(null));
        CompletableFuture<String> cfThen = CompletableFuture.completedFuture("message").thenApply(s -> {
            return s.toUpperCase();
        });
        System.out.println("MESSAGE cfThen "+ cfThen.getNow(null));
    }

    private static void runAsyncMethod() {
        CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
            if (Thread.currentThread().isDaemon()) {
                System.out.println("value of daemon::" + Thread.currentThread().getName());
            }else{
                System.out.println("no daemon");
            }
        });
        if (cf.isDone()){
            System.out.println("task done");
        }else {
            System.out.println("task not done");
        }
    }

    public static Future<String> calculateAsyncWithCancellation() throws InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Thread.sleep(500);
            completableFuture.cancel(false);
            return null;
        });

        return completableFuture;
    }

    public static Future<Employee> calculateAsync() throws InterruptedException {
        CompletableFuture<Employee> completableFuture
                = new CompletableFuture<>();

        Executors.newCachedThreadPool().submit(() -> {
            Employee employee = new Employee();
            Thread.sleep(500);
            employee.setEmpID("1");
            employee.setName("arun");
            completableFuture.complete(employee);
            return null;
        });

        return completableFuture;
    }
}

class Employee{
    private String name;
    private String empID;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }
}
