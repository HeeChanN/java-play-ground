package org.example;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Screening {

    private LocalDate date;
    private LocalTime time;
    private Integer sequence;
    private Movie movie;


    public Reservation reserve(Integer viewerCount){
        Long originalPrice = movie.getAmount();
        Long discountPrice = movie.getAmount(date, time, sequence);
        return new Reservation(date, time, sequence, movie, discountPrice, viewerCount);
    }
}
