package domain.discount;

import domain.orders.Orders;
import domain.payment.PaymentMethod;

public class DiscountGroup {
    private static final AmountDiscountPolicy amountDiscountPolicy = new ChickenAmountDiscountPolicy();
    private static final PaymentMethodDiscountPolicy paymentMethodDiscountPolicy = new CashPaymentMethodPolicy();

    public static double discount(Orders orders, PaymentMethod paymentMethod) {
        double money = orders.getPrice();
        if (amountDiscountPolicy.support(orders)) {
            money = amountDiscountPolicy.discount(orders);
        }

        if (paymentMethodDiscountPolicy.support(paymentMethod)) {
            money = paymentMethodDiscountPolicy.discount(money);
        }
        return money;
    }
}
