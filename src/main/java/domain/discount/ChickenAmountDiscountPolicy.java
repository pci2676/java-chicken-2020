package domain.discount;

import domain.orders.Orders;

public class ChickenAmountDiscountPolicy implements AmountDiscountPolicy {
    private static final int MINIMUM_CHICKEN_AMOUNT = 10;
    private static final int DISCOUNT_PRICE = 10000;
    private static final int FREE = 0;

    @Override
    public boolean support(final Orders orders) {
        return orders.getChickenAmount() >= MINIMUM_CHICKEN_AMOUNT;
    }

    @Override
    public int discount(final Orders orders) {
        int price = orders.getPrice();
        int chickenAmount = orders.getChickenAmount();
        while (chickenAmount >= MINIMUM_CHICKEN_AMOUNT) {
            chickenAmount -= MINIMUM_CHICKEN_AMOUNT;
            price -= DISCOUNT_PRICE;
        }
        return Math.max(price, FREE);
    }
}
