package org.example.discount_condition;

import org.example.Screening;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

public class PeriodCondition implements DiscountCondition {

    private DayOfWeek dayOfWeek;
    private LocalTime startTime;
    private LocalTime endTime;

    @Override
    public boolean check(LocalDate date, LocalTime time, Integer sequence) {
        if(date.getDayOfWeek() == dayOfWeek){
            return time.isAfter(startTime) && time.isBefore(endTime);
        }
        return false;
    }
}
