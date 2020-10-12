package me.oktop.java8inaction.modernjava;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class MethodReferenceTest {

    @Test
    @DisplayName("Method Reference 연습")
    void method_reference_practice_test() {
        //given, when
        List<BigDecimal> collect = Stream.of(new BigDecimal("10.0"), new BigDecimal("11.0"), new BigDecimal("5"))
                .sorted(BigDecimalUtil::compare)
//                .sorted(BigDecimal::compareTo)
                .collect(Collectors.toList());
        //then
        assertThat(collect.get(0)).isEqualTo("5");
    }

    @Test
    @DisplayName("instance 멤버변수 사용 테스트")
    void instance_variable_test() {
        //given, when
        final String targetString = "c";
        boolean isMatch = Stream.of("a", "b", "c", "d")
//                .anyMatch("c"::equals);
                .anyMatch(targetString::equals);

        //then
        assertThat(isMatch).isTrue();
    }

    @Test
    @DisplayName("일급함수 1. 함수를 파라미터로 넘길 수 있어야 한다.")
    void first_class_function_test1() {
        //given,when
        String lambdaUseString = firstClassFunction(3, i -> String.valueOf(i * 2)); // lambda expression

        String methodReferenceUseString = firstClassFunction(3, MethodReferenceTest::doubleThenToString); // method reference. method 를 파라미터로 넘길수 있다.
        //then
        assertThat(lambdaUseString).isEqualTo("6");
        assertThat(methodReferenceUseString).isEqualTo("6");
    }

    @Test
    @DisplayName("일급함수 2. 함수를 결과값으로 받을 수 있어야 한다.")
    void first_class_function_test2() {
        //given
        Function<Integer, String> fl = getDoubleThenToStringUsingLambda();
        Function<Integer, String> fr = getDoubleThenToStringUsingMethodReference();
        //when
        String resultFromFl = fl.apply(10);
        String resultFromFr = fr.apply(20);
        //then
        assertThat(resultFromFl).isEqualTo("20");
        assertThat(resultFromFr).isEqualTo("40");
    }

    @Test
    @DisplayName("일급함수 3. 함수를 데이터스트럭처나 변수에 저장할 수 있어야 한다.")
    void first_class_function_test3() {
        //given
        List<Function<Integer, String>> fsL = Collections.singletonList(i -> String.valueOf(i * 2)); // 함수를 실행한 것이 아님. 함수를 저장만 한것. apply 가 호추 될 때 실행
        List<Function<Integer, String>> frM = Collections.singletonList(MethodReferenceTest::doubleThenToString); // 함수를 실행한 것이 아님. 함수를 저장만 한것. apply 가 호추 될 때 실행
        Function<Integer, String> fLambda = i -> String.valueOf(i * 2);
        Function<Integer, String> frM2 = MethodReferenceTest::doubleThenToString;

        //when
        String resultFromFsl = fsL.get(0).apply(3);
        String resultFromFrM = frM.get(0).apply(4);
        String fLambda2 = fLambda.apply(5); // 변수에 함수를 저장
        String resultFromFrM2 = frM2.apply(6);
        //then
        assertThat(resultFromFsl).isEqualTo("6");
        assertThat(resultFromFrM).isEqualTo("8");
        assertThat(fLambda2).isEqualTo("10");
        assertThat(resultFromFrM2).isEqualTo("12");
    }

    private Function<Integer, String> getDoubleThenToStringUsingMethodReference() {
        return MethodReferenceTest::doubleThenToString;
    }

    private static Function<Integer, String> getDoubleThenToStringUsingLambda() {
        return i -> String.valueOf(i * 2);
    }

    private static String firstClassFunction(int n, Function<Integer, String> function) {
        return function.apply(n);
    }

    private static String doubleThenToString(int i) {
        return String.valueOf(i * 2);
    }
}

class BigDecimalUtil {
    public static int compare(BigDecimal bd1, BigDecimal bd2) {
        return bd1.compareTo(bd2);
    }
}
