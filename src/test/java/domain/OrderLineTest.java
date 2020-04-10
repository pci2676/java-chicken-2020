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
        orderLine.add(new OrderLine(menu, 1));

        //then
        assertThat(orderLine).isEqualTo(new OrderLine(menu, 2));
    }

    @DisplayName("단일메뉴를 추가주문하여 주문 수량의 합이 99개를 넘어가는 경우 Exception 발생")
    @Test
    void name3() {
        //given
        Menu menu = MenuRepository.findByNumber(1L);
        int givenAmount = 99;
        OrderLine orderLine = new OrderLine(menu, givenAmount);
        int addAmount = 1;

        //then
        assertThatThrownBy(() -> orderLine.add(new OrderLine(menu, addAmount)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("단일 메뉴는 99이상 주문 할 수 없습니다.%s현재 갯수 : %d, 추가 갯수 : %d", System.lineSeparator(), givenAmount, addAmount);
    }

    @DisplayName("서로 다른 종류의 메뉴를 추가하려고 하면 Exception 발생")
    @Test
    void name5() {
        //given
        Menu menu = MenuRepository.findByNumber(1L);
        int givenAmount = 1;
        OrderLine orderLine = new OrderLine(menu, givenAmount);

        //when
        Menu otherMenu = MenuRepository.findByNumber(2L);
        OrderLine otherOrderLine = new OrderLine(otherMenu, 1);

        //then
        assertThatThrownBy(() -> orderLine.add(otherOrderLine))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("동일 메뉴에 대해서만 갯수를 추가할 수 있습니다.%s기존 메뉴 : %s, 추가시도 메뉴 : %s", System.lineSeparator(), orderLine.getMenuName(), otherOrderLine.getMenuName());
    }
}