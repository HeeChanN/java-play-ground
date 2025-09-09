package org.example.discount_policy;

import org.example.Money;
import org.example.Screening;
import org.example.discount_condition.DiscountCondition;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class DiscountPolicy {
    private List<DiscountCondition> conditions = new ArrayList<>();

    public DiscountPolicy(DiscountCondition... discountConditions){
        this.conditions = Arrays.asList(discountConditions);
    }

    public Money calculateDiscountAmount(Screening screening){
        for(DiscountCondition condition : conditions){
            if(condition.isSatisfiedBy(screening)){
                return getDiscountAmount(screening);
            }
        }
        return Money.ZERO;
    }

    abstract protected Money getDiscountAmount(Screening screening);
}
