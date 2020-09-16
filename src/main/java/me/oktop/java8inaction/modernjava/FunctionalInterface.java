package me.oktop.java8inaction.modernjava;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterface {

    public static int toInt(String s) {
        Function<String, Integer> function = v -> Integer.parseInt(v);
        return function.apply(s);
    }

    public static String printAndToString(List<String> list) {
        StringBuilder sb = new StringBuilder();
        Consumer<List<String>> consumer = e -> e.forEach(sb::append);
        consumer.accept(list);
        return sb.toString();
    }

    public static <T> List<T> filter(List<T> numbers, Predicate<T> predicate) {
        List<T> list = new ArrayList<>();
        numbers.forEach(v -> {
            if (predicate.test(v)) {
                list.add(v);
            }
        });
        return list;
    }

    public static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hayun";
    }

    public static <T> void printIfValidIndex(int number, Supplier<T> supplier) {
        if (number == 0) {
            System.out.println("the value is " + supplier.get());
        } else {
            System.out.println("Invalid");
        }
    }


    public static void main(String[] args) {
        // apply
        Function<String, Integer> toInt = value -> Integer.parseInt(value);
        int number = toInt.apply("100");
        System.out.println(number);

        // identity
//        Function<Integer, Integer> identity = Function.identity();
        Function<Integer, Integer> identity = t -> t;
        Integer num = identity.apply(999);
        System.out.println(num);

        // consumer. 소비하는것 리턴값이 없다. 출력만
        Consumer<String> print = value -> System.out.println(value);
        Consumer<String> greetings = value -> System.out.println("Hello " + value);
        print.accept("hello");
        greetings.accept("world");

        Consumer<List<String>> consumer = v -> System.out.println(v);
        List<String> s = Arrays.asList("1, 2, 3");
        consumer.accept(s);

        // predicate
        Predicate<String> predicate = value -> "test".equals(value);
        boolean isA = predicate.test("a");
        boolean isTest = predicate.test("test");
        System.out.println(isA);
        System.out.println(isTest);

        Predicate<Integer> isPositivePredicate = value -> value > 0;
        Predicate<Integer> isLessThan = value -> value < 3;
        Function<Integer, Boolean> function = value -> value > 0;
        boolean isPositive = isPositivePredicate.test(-1);
        boolean isPositiveUseFunction = function.apply(1);
        System.out.println(isPositive);
        System.out.println(isPositiveUseFunction);

        List<Integer> numbers = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8);
        System.out.println("positive integers : " + filter(numbers, isPositivePredicate));
        System.out.println("lessthan integers : " + filter(numbers, isLessThan));

        //Supplier
        long start = System.currentTimeMillis();
        printIfValidIndex(0, () -> getVeryExpensiveValue());
        printIfValidIndex(1, () -> getVeryExpensiveValue());
        printIfValidIndex(2, () -> getVeryExpensiveValue());
        System.out.println((System.currentTimeMillis() - start) / 1000 + "초");
    }

}
