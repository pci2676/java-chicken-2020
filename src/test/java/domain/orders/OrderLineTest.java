package domain.orders;

import domain.menu.Category;
import domain.menu.Menu;
import domain.menu.MenuRepository;
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

    @DisplayName("해당 메뉴의 할인 전 총 주문 금액을 반환한다.")
    @Test
    void name6() {
        //given
        int defaultPrice = 1000;
        Menu menu = new Menu(1L, "test", Category.CHICKEN, defaultPrice);
        int amount = 2;
        OrderLine orderLine = new OrderLine(menu, amount);

        //when
        int price = orderLine.getPrice();
        int expect = defaultPrice * amount;

        //then
        assertThat(price).isEqualTo(expect);
    }
}