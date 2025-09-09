package org.example;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation {
    private String title;
    private Integer viewerCount;
    private Long price;
    private Long discountPrice;
    private String info;

    public Reservation(LocalDate date, LocalTime time, Integer sequence, Movie movie, Long discountPrice,Integer viewerCount) {
        this.title = movie.getTitle();
        this.viewerCount = viewerCount;
        this.price = movie.getAmount();
        this.discountPrice = discountPrice;
        this.info = makeInfo(date, time, sequence, movie);
    }

    private String makeInfo(LocalDate date, LocalTime time, Integer sequence, Movie movie){
        int startTime = time.getHour() * 60 + time.getMinute();
        int endTime = startTime + movie.getRunningTime();
        return String.format("%s년 %s월 %s일 (%s) %s회 %s %s", date.getYear(), date.getMonthValue(),date.getDayOfMonth(), date.getDayOfWeek(),sequence, startTime, endTime);
    }
}
