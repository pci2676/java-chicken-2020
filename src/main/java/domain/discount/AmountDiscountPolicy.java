package domain.discount;

import domain.orders.Orders;

public interface AmountDiscountPolicy {
    boolean support(Orders orders);

    int discount(Orders orders);
}
