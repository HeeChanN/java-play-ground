package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Screening {

    private LocalDateTime whenScreened;
    private Integer sequence;
    private Movie movie;

    public Screening(LocalDateTime whenScreened, Integer sequence, Movie movie) {
        this.whenScreened = whenScreened;
        this.sequence = sequence;
        this.movie = movie;
    }

    public LocalDateTime getStartTime() {
        return whenScreened;
    }

    public boolean isSequence(Integer sequence){
        return this.sequence == sequence;
    }

    public Money getMovieFee(){
        return movie.getFee();
    }

    //    public Reservation reserve(Integer viewerCount){
//        Long originalPrice = movie.getAmount();
//        Long discountPrice = movie.getAmount(date, time, sequence);
//        return new Reservation(date, time, sequence, movie, discountPrice, viewerCount);
//    }

    public Reservation reserve(Customer customer, int audienceCount){
        return new Reservation(customer, this, calculateFee(audienceCount), audienceCount);
    }

    private Money calculateFee(int audienceCount){
        return movie.calculateMovieFee(this).times(audienceCount);
    }
}
