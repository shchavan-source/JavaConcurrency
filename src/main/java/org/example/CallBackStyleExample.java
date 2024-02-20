package main.java.org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.IntConsumer;

public class CallBackStyleExample {
    public static void main(String[] args) throws Exception {

        Result result = new Result();

        f(2, (int x) -> {
            result.left = x;
            System.out.println("f(): " + (result.left + result.right));
        });

        g(3, (int y) -> {
            result.right = y;

            System.out.println("g(): "+(result.left + result.right));
        });

        System.out.println("Hello world!");
    }

    static void f(int x, IntConsumer dealWithResult) throws Exception{

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            dealWithResult.accept(x*2);
        });
        executorService.shutdown();
    }

    static void g(int y, IntConsumer dealWithResult) throws Exception{

        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            dealWithResult.accept(y*2);
        });
        executorService.shutdown();
    }

    public static class Result {
        private int left;
        private int right;
    }
}