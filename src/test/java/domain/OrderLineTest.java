package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderLineTest {

    @DisplayName("단일 메뉴에 대한 주문 생성")
    @Test
    void name() {
        //given
        Menu menu = MenuRepository.findByNumber(1L);
        int amount = 1;

        //when
        OrderLine orderLine = new OrderLine(menu, amount);

        //then
        assertThat(orderLine).isEqualTo(new OrderLine(menu, amount));
    }


    @DisplayName("단일 메뉴의 주문 수량이 0개 이하인 경우 Exception")
    @Test
    void name1() {
        //given
        Menu menu = MenuRepository.findByNumber(1L);
        int wrongAmount = 0;

        //then
        assertThatThrownBy(() -> new OrderLine(menu, wrongAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 수량 : %d%s메뉴의 주문 갯수는 0개 이상이어야 합니다.", wrongAmount, System.lineSeparator());
    }

    @DisplayName("단일 메뉴의 주문 수량이 99개 이상인 경우 Exception")
    @Test
    void name2() {
        //given
        Menu menu = MenuRepository.findByNumber(1L);
        int wrongAmount = 100;

        //then
        assertThatThrownBy(() -> new OrderLine(menu, wrongAmount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문 수량 : %d%s메뉴의 주문 갯수는 99개 이하여야 합니다.", wrongAmount, System.lineSeparator());
    }

    @DisplayName("단일 메뉴의 갯수를 추가 주문한다.")
    @Test
    void name4() {
        //given
        Menu menu = MenuRepository.findByNumber(1L);
        int givenAmount = 1;
        OrderLine orderLine = new OrderLine(menu, givenAmount);

        //when
        orderLine.addAmount(1);

        //then
        assertThat(orderLine).isEqualTo(new OrderLine(menu, 2));
    }

    @DisplayName("추가된 단일 메뉴의 주문 수량의 합이 99개를 넘어가는 경우 Exception 발생")
    @Test
    void name3() {
        //given
        Menu menu = MenuRepository.findByNumber(1L);
        int givenAmount = 99;
        OrderLine orderLine = new OrderLine(menu, givenAmount);
        int addAmount = 1;

        //then
        assertThatThrownBy(() -> orderLine.addAmount(addAmount))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("단일 메뉴는 99이상 주문 할 수 없습니다.%s현재 갯수 : %d, 추가 갯수 : %d", System.lineSeparator(), givenAmount, addAmount);
    }
}