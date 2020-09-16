package me.oktop.java8inaction.modernjava;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class FunctionalInterfaceTest {

    @Test
    @DisplayName("Function 함수 테스트")
    void toInt_test() {
        //given
        String s = "12345";
        //when
        int actual = FunctionalInterface.toInt(s);
        //then
        assertThat(actual, is(12345));
    }

    @Test
    @DisplayName("컨슈머 함수 테스트")
    void printAndToString_test() {
        //given
        List<String> list = Arrays.asList("1", "2", "3");
        //when
        String actual = FunctionalInterface.printAndToString(list);
        //then
        assertThat(actual, is("123"));
    }

    @Test
    @DisplayName("Predicate 함수 테스트")
    void filter() {
        //given
        List<Integer> list = Arrays.asList(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5);
        //when
        List<Integer> filter = FunctionalInterface.filter(list, v -> v > 0);
        //then
        assertThat(filter.size(), is(5));
        assertThat(filter.get(3), is(4));
    }

    @Test
    @DisplayName("Supplier 함수 테스트")
    void printIfValidIndex() {
        //given
        int num1 = 0;
        int num2 = 1;
        int num3 = 2;

        //when
        long start = System.currentTimeMillis();
        FunctionalInterface.printIfValidIndex(num1, FunctionalInterface::getVeryExpensiveValue);
        FunctionalInterface.printIfValidIndex(num2, FunctionalInterface::getVeryExpensiveValue);
        FunctionalInterface.printIfValidIndex(num3, FunctionalInterface::getVeryExpensiveValue);
        long end = (System.currentTimeMillis() - start) / 1000;
        //then
        assertThat(end, is(3L));
    }
}