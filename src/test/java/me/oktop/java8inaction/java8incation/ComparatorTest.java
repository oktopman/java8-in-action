package me.oktop.java8inaction.java8incation;

import me.oktop.java8inaction.java8inaction.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class ComparatorTest {

    public static void main(String[] args) {
        Apple apple1 = new Apple(10, "red");
        Apple apple2 = new Apple(50, "blue");
        Apple apple3 = new Apple(30, "red");
        Apple apple4 = new Apple(90, "blue");
        List<Apple> apples = Arrays.asList(apple1, apple2, apple3, apple4);
        apples.sort(Comparator.comparing(Apple::getWeight));
        apples.forEach(System.out::println);

        Predicate<Apple> predicate = apple -> "red".equals(apple.getColor());
        Predicate<Apple> predicate2 = apple -> "blue".equals(apple.getColor());
        verify(apples, predicate);
        verify(apples, predicate2);

    }

    public static <T> void verify(List<T> list, Predicate<T> predicate) {
        for (T apple : list) {
            if (predicate.test(apple)) {
                System.out.println("red");
            } else {
                System.out.println("no red");
            }
        }
    }
}
