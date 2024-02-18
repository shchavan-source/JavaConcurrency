package main.java.org.example;

import java.util.function.IntConsumer;

public class CallBackStyleExample {
    public static void main(String[] args) throws Exception {

        Result result = new Result();

        f(2, (int x) -> {
            result.left = x;
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("f(): " + result.left + result.right);
        });

        g(3, (int y) -> {
            result.right = y;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("g(): "+result.left + result.right);
        });

        System.out.println("Hello world!");
    }

    static void f(int x, IntConsumer dealWithResult) throws Exception{

        dealWithResult.andThen(value -> System.out.println("first then" + value+1))
                .andThen(value -> System.out.println("2nd then " + value+1))
                .accept(x*2);
    }

    static void g(int y, IntConsumer dealWithResult) throws Exception{

        dealWithResult.accept(y*2);
    }

    public static class Result {
        private int left;
        private int right;
    }
}