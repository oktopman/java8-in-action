package me.oktop.java8inaction.modernjava;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class WhyJava8 {

    void printByJava7(List<Integer> numbers) {
        StringBuilder sb = new StringBuilder();
        for (Integer number : numbers) {
            sb.append(number).append(" : ");
        }
        String s = sb.substring(0, sb.length() - 3);
        System.out.println(s);
    }

    void printByJava8(List<Integer> numbers) {
        String s = numbers.stream().map(String::valueOf).collect(joining(" : "));
        System.out.println(s);
    }
    public static void main(String[] args) {
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 1 : 2 : 3 : 4 출력
        WhyJava8 whyJava8 = new WhyJava8();
        whyJava8.printByJava7(numbers);
        whyJava8.printByJava8 (numbers);
    }
}
