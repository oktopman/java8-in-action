package me.oktop.java8inaction.thejava8;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamPractice {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "spring api developemtn", false));

        System.out.println("spring 으로 시작하는 수업");
        springClasses.stream()
                .filter(o -> o.getTitle().startsWith("spring"))
                .forEach(o -> System.out.println(o.getId()));

        System.out.println("close 되지않은 수업");
        springClasses.stream()
                .filter(o -> !o.isClosed())
                .forEach(o -> System.out.println(o.getId()));


        System.out.println("수업 이름만 모아서 스트림 만들기");
        springClasses.stream()
                .map(OnlineClass::getTitle)
                .forEach(System.out::println);

        List<OnlineClass> javaClasses = new ArrayList<>();
        javaClasses.add(new OnlineClass(6, "The Java, Test", true));
        javaClasses.add(new OnlineClass(7, "The Java, Code manipulation", true));
        javaClasses.add(new OnlineClass(8, "The Java, 8 to 11", false));

        List<List<OnlineClass>> hayunEvents = new ArrayList<>();
        hayunEvents.add(springClasses);
        hayunEvents.add(javaClasses);

        System.out.println("두 수업 목록에 들어있는 모든 수업 아이디 출력");
        hayunEvents.stream()
                .flatMap(Collection::stream)
                .forEach(OnlineClass::getTitle);

        System.out.println("10부터 1씩 증가하는 무제한 스트림 중에서 앞에 10개 빼고 최대 10개 까지만");
        Stream.iterate(10, i -> i + 1)
                .skip(10)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("자바 수업 중에 Test가 들어있는 수업이 있는지 확인");
        boolean match = javaClasses.stream()
                .anyMatch(o -> o.getTitle().contains("Test1"));
        System.out.println(match);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 것만 모아서 List로 만들기");
        List<String> stringList = springClasses.stream()
                .filter(o -> o.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.toList());

        stringList.forEach(System.out::println);

        System.out.println("스프링 수업 중에 제목에 spring이 들어간 것만 모아서 ,붙이기");
        String joining = springClasses.stream()
                .filter(o -> o.getTitle().contains("spring"))
                .map(OnlineClass::getTitle)
                .collect(Collectors.joining(","));
        System.out.println(joining);
    }
}
