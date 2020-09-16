package me.oktop.java8inaction.modernjava;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class CalcutationTest {

    @Test
    void additionCalculateTest() {
        //given
        Calculation calculation = new Addition();
        //when
        int actual = calculation.calculate(1, 2);
        //then
        assertThat(actual, is(3));
    }

    @Test
    void subtractionCalculateTest() {
        //given
        Calculation calculation = new Subtraction();
        //when
        int actual = calculation.calculate(1, 2);
        //then
        assertThat(actual, is(-1));
    }

    @Test
    void multiplicationCalculateTest() {
        //given
        Calculation calculation = new Multiplication();
        //when
        int actual = calculation.calculate(3, 2);
        //then
        assertThat(actual, is(6));
    }

    @Test
    void divisionCalculateTest() {
        //given
        Calculation calculation = new Division();
        //when
        int actual = calculation.calculate(8, 4);
        //then
        assertThat(actual, is(2));
    }
}