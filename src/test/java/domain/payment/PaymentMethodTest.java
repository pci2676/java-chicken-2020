package domain.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentMethodTest {

    @DisplayName("결제수단이 현금인지 확인")
    @ParameterizedTest
    @CsvSource(value = {"CASH,true", "CARD,false"})
    void isCash(PaymentMethod paymentMethod, boolean expect) {
        assertThat(paymentMethod.isCash()).isEqualTo(expect);
    }
}