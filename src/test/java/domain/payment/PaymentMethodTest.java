package domain.payment;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaymentMethodTest {

    @DisplayName("결제수단이 현금인지 확인")
    @ParameterizedTest
    @CsvSource(value = {"CASH,true", "CARD,false"})
    void isCash(PaymentMethod paymentMethod, boolean expect) {
        assertThat(paymentMethod.isCash()).isEqualTo(expect);
    }

    @DisplayName("결제수단 번호로 결제수단 찾기")
    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    void findByNumber(int methodNumber) {
        PaymentMethod paymentMethod = PaymentMethod.findByNumber(methodNumber);

        assertThat(paymentMethod).isInstanceOf(PaymentMethod.class);
    }

    @DisplayName("존재하지 않는 결제수단 번호로 결제수단 찾을시 Exception")
    @Test
    void findByNumber1() {
        assertThatThrownBy(() -> PaymentMethod.findByNumber(3))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("%d : 잘못된 결제수단을 선택하였습니다.", 3);
    }
}