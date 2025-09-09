package org.example.discount_condition;

import org.example.Screening;

import java.time.LocalDate;
import java.time.LocalTime;

public interface DiscountCondition {
    public boolean isSatisfiedBy(Screening screening);
}
