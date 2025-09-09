package org.example.discount_policy;

public class AmountDiscountPolicy implements DiscountPolicy {

    private Long discountAmount;

    @Override
    public Long discount(Long price) {
        return price - discountAmount;
    }
}
