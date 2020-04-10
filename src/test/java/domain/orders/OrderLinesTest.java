package domain.orders;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class OrderLinesTest {

    @DisplayName("동일 메뉴 추가시 기존 메뉴의 갯수를 추가한다.")
    @Test
    void name() {
        //given
        Menu menu = MenuRepository.findByNumber(1L);
        OrderLine givenOrderLine = new OrderLine(menu, 1);
        OrderLines orderLines = new OrderLines(new ArrayList<>(Arrays.asList(givenOrderLine)));

        //when
        orderLines.add(new OrderLine(menu, 1));

        //then
        assertThat(orderLines).isEqualTo(new OrderLines(Arrays.asList(new OrderLine(menu, 2))));
    }

    @DisplayName("기존 메뉴와 다른 메뉴 추가시 서로 다른 두종류의 메뉴가 저장된다.")
    @Test
    void name1() {
        //given
        Menu menu = MenuRepository.findByNumber(1L);
        OrderLine givenOrderLine = new OrderLine(menu, 1);
        OrderLines orderLines = new OrderLines(new ArrayList<>(Arrays.asList(givenOrderLine)));

        //when
        Menu otherMenu = MenuRepository.findByNumber(2L);
        orderLines.add(new OrderLine(otherMenu, 1));

        //then
        assertThat(orderLines).isEqualTo(new OrderLines(Arrays.asList(new OrderLine(menu, 1), new OrderLine(otherMenu, 1))));
    }

    @DisplayName("복합 메뉴에 대한 할인 전 총 주문 금액 구하기")
    @Test
    void name2() {
        //given
        Menu menu1 = MenuRepository.findByNumber(1L);
        int amount1 = 2;
        OrderLine orderLine1 = new OrderLine(menu1, amount1);

        Menu menu2 = MenuRepository.findByNumber(2L);
        int amount2 = 2;
        OrderLine orderLine2 = new OrderLine(menu2, amount2);

        OrderLines orderLines = new OrderLines(new ArrayList<>(Arrays.asList(orderLine1, orderLine2)));

        //when
        int price = orderLines.calculatePrice();
        int expect = menu1.calculatePrice(amount1) + menu2.calculatePrice(amount2);

        //then
        assertThat(price).isEqualTo(expect);
    }
}