package me.oktop.java8inaction.java8incation;

import me.oktop.java8inaction.java8inaction.Trader;
import me.oktop.java8inaction.java8inaction.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StreamTest {
    private List<Transaction> transactions = new ArrayList<>();

    @BeforeEach
    public void setup() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    void 이천십일년에_일어난_모든_트랜잭션_오름차순_테스트() {
        List<Transaction> list = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());

        assertThat(list.size()).isEqualTo(2);
        assertThat(list.get(0).getValue()).isEqualTo(300);
        assertThat(list.get(1).getValue()).isEqualTo(400);
    }

    @Test
    void 거래자가_근무하는_모든_도시를_중복없이_나열_테스트() {
        List<String> list = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());

        assertThat(list.size()).isEqualTo(2);
    }

    @Test
    void 캠프릿지_모든근무자_이름순으로_정렬_테스트() {
        List<Trader> list = transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());

        assertThat(list.get(0).getName()).isEqualTo("Alan");
        assertThat(list.get(1).getName()).isEqualTo("Brian");
        assertThat(list.get(3).getName()).isEqualTo("Raoul");
    }

//    @Test
//    void 모든거래자_이름_알파벳순_정렬_테스트() {
//        List<Trader> list = transactions.stream()
//                .map(Transaction::getTrader)
//                .sorted(Comparator.comparing(Trader::getName))
//                .collect(Collectors.toList());
//
//        assertThat(list.get(0).getName()).isEqualTo("Alan");
//        assertThat(list.get(1).getName()).isEqualTo("Brian");
//        assertThat(list.get(1).getName()).isEqualTo("Mario");
//        assertThat(list.get(3).getName()).isEqualTo("Raoul");
//    }

    @Test
    void 밀라노에_거래자가_있는지_테스트() {
        boolean exits = transactions.stream()
                .anyMatch(transaction -> "Milan".equals(transaction.getTrader().getCity()));

        assertTrue(exits);
    }

    @Test
    void 케임브리지_거주하는거래자_모든트랜잭션값_출력_테스트() {
        transactions.stream()
                .filter(transaction -> "Cambridge".equals(transaction.getTrader().getCity()))
                .map(Transaction::getValue)
                .forEach(System.out::println);
    }

    @Test
    void 전체_트랜잭션_최대값_테스트() {
        Optional<Transaction> max = transactions.stream()
                .max(Comparator.comparing(Transaction::getValue));

        assertThat(max.get().getValue()).isEqualTo(1000);
    }

    @Test
    void 전체_트랜잭션_최소값_테스트() {
        Optional<Transaction> max = transactions.stream()
                .min(Comparator.comparing(Transaction::getValue));

        assertThat(max.get().getValue()).isEqualTo(300);
    }

}
