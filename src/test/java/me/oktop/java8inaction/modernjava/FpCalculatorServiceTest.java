package me.oktop.java8inaction.modernjava;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class FpCalculatorServiceTest {

    @Test
    void fpCalculateServiceTest() {
        //given
        FpCalculatorService fpCalculatorService = new FpCalculatorService();
        //when
        int actual = fpCalculatorService.calculate(new Addition(), 11, 2);
        int actual2 = fpCalculatorService.calculate((num1, num2) -> num1 + num2, 11, 4);
        //then
        assertThat(actual, is(13));
        assertThat(actual2, is(15));
    }
}