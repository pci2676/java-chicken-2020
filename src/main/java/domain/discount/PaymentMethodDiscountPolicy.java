package domain.discount;

import domain.payment.PaymentMethod;

public interface PaymentMethodDiscountPolicy {
    boolean support(PaymentMethod paymentMethod);

    double discount(double price);
}
