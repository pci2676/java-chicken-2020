package domain.orders;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderAmountTest {

    @DisplayName("주문 수량이 0개 이하인 경우 Exception")
    @Test
    void name1() {
        //given
        int wrongAmount = 0;

        //then
        assertThatThrownBy(() -> OrderAmount.of(wrongAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 수량 : %d%s메뉴의 주문 갯수는 0개 이상이어야 합니다.", wrongAmount, System.lineSeparator());
    }

    @DisplayName("주문 수량이 99개 이상인 경우 Exception")
    @Test
    void name2() {
        //given
        int wrongAmount = 100;

        //then
        assertThatThrownBy(() -> OrderAmount.of(wrongAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 수량 : %d%s메뉴의 주문 갯수는 99개 이하여야 합니다.", wrongAmount, System.lineSeparator());
    }

    @DisplayName("메뉴를 추가주문하여 주문 수량의 합이 99개를 넘어가는 경우 Exception 발생")
    @Test
    void name3() {
        //given
        int givenAmount = 99;
        OrderAmount orderAmount = OrderAmount.of(givenAmount);

        int addAmount = 1;
        OrderAmount addOrderAmount = OrderAmount.of(addAmount);

        //then
        assertThatThrownBy(() -> orderAmount.plus(addOrderAmount))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("단일 메뉴는 99이상 주문 할 수 없습니다.%s현재 갯수 : %d, 추가 갯수 : %d", System.lineSeparator(), givenAmount, addAmount);
    }
}