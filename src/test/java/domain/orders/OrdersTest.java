package domain.orders;

import domain.menu.Category;
import domain.menu.Menu;
import domain.menu.MenuRepository;
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
        orders.add(otherOrderLine);

        //then
        assertThat(orders).isEqualTo(new Orders(Arrays.asList(orderLine, otherOrderLine)));
    }

    @DisplayName("치킨종류의 메뉴의 총 주문수량을 구한다")
    @Test
    void name3() {
        //given
        Menu chicken1 = new Menu(1L, "chicken", Category.CHICKEN, 1000);
        Menu chicken2 = new Menu(1L, "chicken", Category.CHICKEN, 1000);
        Menu notChicken = new Menu(1L, "not chicken", Category.BEVERAGE, 1000);

        OrderLine orderLine1 = new OrderLine(chicken1, 1);
        OrderLine orderLine2 = new OrderLine(chicken2, 1);
        OrderLine orderLine3 = new OrderLine(notChicken, 1);

        Orders orders = new Orders(new ArrayList<>(Arrays.asList(orderLine1, orderLine2, orderLine3)));

        //when
        int amount = orders.getChickenAmount();

        //then
        assertThat(amount).isEqualTo(2);
    }
}