package org.example.discount_policy;

public class PercentDiscountPolicy implements DiscountPolicy {

    private Long discountPercent;

    @Override
    public Long discount(Long price) {
        return (price * (100 - discountPercent))/ 100L;
    }
}
