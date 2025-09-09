package org.example.discount_condition;

import java.time.LocalDate;
import java.time.LocalTime;

public class SequenceCondition implements DiscountCondition {

    private Integer required;

    @Override
    public boolean check(LocalDate date, LocalTime time, Integer sequence) {
        return this.required == sequence;
    }
}
