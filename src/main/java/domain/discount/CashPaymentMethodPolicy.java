package domain.discount;

import domain.payment.PaymentMethod;

public class CashPaymentMethodPolicy implements PaymentMethodDiscountPolicy {

    private static final double DISCOUNT_RATE = 0.95;

    @Override
    public boolean support(final PaymentMethod paymentMethod) {
        return paymentMethod.isCash();
    }

    @Override
    public double discount(final double price) {
        return price * DISCOUNT_RATE;
    }
}
