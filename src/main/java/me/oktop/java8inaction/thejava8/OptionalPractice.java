package me.oktop.java8inaction.thejava8;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OptionalPractice {

    public static void main(String[] args) {
        List<OnlineClass> springClasses = new ArrayList<>();
        springClasses.add(new OnlineClass(1, "spring boot", true));
        springClasses.add(new OnlineClass(2, "spring data jpa", true));
        springClasses.add(new OnlineClass(3, "spring mvc", false));
        springClasses.add(new OnlineClass(4, "spring core", false));
        springClasses.add(new OnlineClass(5, "spring api developemtn", false));

        Optional<OnlineClass> optional = springClasses.stream()
                .filter(o -> o.getTitle().startsWith("spring"))
                .findFirst();

        boolean present = optional.isPresent();
        System.out.println(present);

        OnlineClass onlineClass = optional.get(); // 데이터가 없다면 NoSuchElementException. RuntimeException 계열
        System.out.println(onlineClass.getId());

        // get 말고 optional에 있는 메소드로 데이터를 꺼내는것을 권장
        optional.ifPresent(o -> System.out.println(o.getTitle()));
        OnlineClass orElse = optional.orElse(new OnlineClass(1, "aaa", true)); // 새로 생성
        OnlineClass orElseThrow = optional.orElseThrow(IllegalArgumentException::new);
        Optional<OnlineClass> filter = optional.filter(OnlineClass::isClosed);


    }
}
