package me.oktop.java8inaction.modernjava;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class StreamStudy {

    public static Integer imperative(List<Integer> numbers) {
        Integer result = null;
        for (Integer number : numbers) {
            if (number > 3 && number < 9) {
                final Integer newNumber = number * 2;
                if (newNumber > 10) {
                    result = newNumber;
                    break;
                }
            }
        }
        return result;
    }

    public static Optional<Integer> functional(List<Integer> numbers) {
        return numbers.stream()
                .filter(number -> number > 3)
                .filter(number -> number < 9)
                .map(number -> number * 2)
                .filter(number -> number > 10)
                .findFirst();
    }

    public static void stream() {
        System.out.println(
                Stream.of(1, 2, 3, 4, 5)
                        .filter(i -> i > 3)
                        .map(i -> i * 2)
                        .map(i -> "# " + i)
                        .collect(joining(",")) // terminal
        );

        System.out.println(
                Stream.of(1, 2, 3, 4, 5)
                        .filter(i -> i > 3)
                        .map(i -> i * 2)
                        .map(i -> "# " + i)
                        .collect(joining(",", "[", "]")) // terminal
        );

        System.out.println(
                Stream.of(1, 2, 3, 4, 5)
                        .filter(i -> i > 3)
                        .map(i -> i * 2)
                        .map(i -> "# " + i)
                        .collect(toList()) // terminal
        );

        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 5, 5)
                        .filter(i -> i > 3)
                        .map(i -> i * 2)
                        .map(i -> "# " + i)
                        .distinct()
                        .collect(toList()) // terminal
        );

        final Integer integer127 = 127;
        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 127, 128, 129)
                        .filter(i -> i.equals(integer127))
                        .findFirst()
        );

        final Integer integer128 = 128;
        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 127, 128, 129)
                        .filter(i -> i.equals(integer128))
                        .findFirst()
        );

        System.out.println(
                Stream.of(1, 2, 3, 4, 5, 127, 128, 129)
                        .filter(i -> i > integer127)
                        .count()
        );
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(imperative(numbers));
        System.out.println(functional(numbers).get());
        stream();
    }
}
