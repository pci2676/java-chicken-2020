package domain.discount;

import domain.payment.PaymentMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CashPaymentMethodPolicyTest {

    @DisplayName("결제수단이 현금이라면 할인 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"CASH,true", "CARD,false"})
    void support(PaymentMethod paymentMethod, boolean expect) {
        //given
        PaymentMethodDiscountPolicy paymentMethodDiscountPolicy = new CashPaymentMethodPolicy();

        //when
        boolean actual = paymentMethodDiscountPolicy.support(paymentMethod);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("결제수단이 현금인경우 전체금액의 5%를 할인해 준다.")
    @Test
    void discount() {
        //given
        int price = 10000;

        //when
        PaymentMethodDiscountPolicy paymentMethodDiscountPolicy = new CashPaymentMethodPolicy();
        double discountedPrice = paymentMethodDiscountPolicy.discount(price);

        //then
        assertThat(discountedPrice).isEqualTo(10000 * 0.95);
    }
}