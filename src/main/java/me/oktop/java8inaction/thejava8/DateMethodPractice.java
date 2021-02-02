package me.oktop.java8inaction.thejava8;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class DateMethodPractice {

    public static void main(String[] args) {
        // 기계용 API
        Instant instant = Instant.now();
        System.out.println("기계용 시간 : " + instant); // 기준시 UTC 또는 GMT 라고 부름. 둘다 같은것

        ZoneId zone = ZoneId.systemDefault();
        System.out.println("현재 zone : " + zone);
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Seoul"));
        System.out.println("서울의 현재 시간(기계용) : " + zonedDateTime);

        // 사람용
        LocalDateTime now = LocalDateTime.now();
        System.out.println("LocalDateTime : " + now);

        // 특정 zone의 시간을 보고싶을때
        ZonedDateTime nowInAmerica = ZonedDateTime.now(ZoneId.of("America/Los_Angeles"));
        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        System.out.println("미국의 현재시간 : " + nowInAmerica);
        System.out.println("한국의 현재시간 : " + nowInKorea);

        LocalDate today = LocalDate.now();
        LocalDate thisYearBirthday = LocalDate.of(2021, Month.OCTOBER, 6);

        Period period = Period.between(today, thisYearBirthday);
        System.out.println("이번달부터 내 생일까지 남은 달 : " + period.getMonths());
        System.out.println("오늘로부터 내 생일까지 남은 날짜 : " + (ChronoUnit.DAYS.between(today, thisYearBirthday)));

    }
}
