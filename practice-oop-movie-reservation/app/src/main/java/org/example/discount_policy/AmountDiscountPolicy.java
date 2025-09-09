package org.example.discount_policy;

import org.example.Money;
import org.example.Screening;
import org.example.discount_condition.DiscountCondition;

public class AmountDiscountPolicy extends DiscountPolicy {

    private Money discountAmount;

    public AmountDiscountPolicy(Money discountAmount, DiscountCondition... discountConditions) {
        super(discountConditions);
        this.discountAmount = discountAmount;
    }

    @Override
    protected Money getDiscountAmount(Screening screening) {
        return null;
    }
}
