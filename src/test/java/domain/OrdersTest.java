package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class OrdersTest {

    @DisplayName("단일 메뉴 추가시 보유하는 주문목록은 1개")
    @Test
    void name() {
        //given
        Menu menu = MenuRepository.findByNumber(1L);
        int amount = 1;
        OrderLine orderLine = new OrderLine(menu, amount);

        //when
        Orders orders = new Orders(Arrays.asList(orderLine));

        //then
        assertThat(orders).isEqualTo(new Orders(Arrays.asList(new OrderLine(menu, 1))));
    }

    @DisplayName("단일메뉴에 다른 메뉴를 추가시 주문 목록은 2개이다.")
    @Test
    void name1() {
        //given
        Menu menu = MenuRepository.findByNumber(1L);
        OrderLine orderLine = new OrderLine(menu, 1);
        Orders orders = new Orders(new ArrayList<>(Arrays.asList(orderLine)));

        //when
        Menu otherMenu = MenuRepository.findByNumber(2L);
        OrderLine otherOrderLine = new OrderLine(otherMenu, 1);
        orders.addOrderLine(otherOrderLine);

        //then
        assertThat(orders).isEqualTo(new Orders(Arrays.asList(orderLine, otherOrderLine)));
    }
}