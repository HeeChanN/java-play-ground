package org.example;

import org.example.discount_condition.DiscountCondition;
import org.example.discount_policy.DiscountPolicy;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String title;
    private Integer runningTime;
    private Money fee;
    private DiscountPolicy discountPolicy;

    public Movie(String title, Integer runningTime, Money fee, DiscountPolicy discountPolicy) {
        this.title = title;
        this.runningTime = runningTime;
        this.fee = fee;
        this.discountPolicy = discountPolicy;
    }

    public Money getFee(){
        return fee;
    }

    public Money calculateMovieFee(Screening screening){
        return fee.minus(discountPolicy.calculateDiscountAmount(screening));
    }

//    public Integer getRunningTime(){
//        return this.runningTime;
//    }
//
//    public String getTitle(){
//        return title;
//    }
//
//    public Long getAmount(LocalDate date, LocalTime time, Integer sequence){
//        if(!hasDiscountPolicy()){
//            return price;
//        }
//        return discount(date, time, sequence);
//    }
//
//    private boolean hasDiscountPolicy(){
//        return discountPolicy != null;
//    }
//
//    private Long discount(LocalDate date, LocalTime time, Integer sequence){
//        for(DiscountCondition condition : discountConditions){
//            if(condition.check(date, time, sequence)){
//                discountPolicy.discount(price);
//            }
//        }
//        return this.price;
//    }
}
