package domain.discount;

import domain.menu.Category;
import domain.menu.Menu;
import domain.orders.OrderLine;
import domain.orders.Orders;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class ChickenAmountDiscountPolicyTest {

    @DisplayName("치킨메뉴의 갯수가 10개 이상이면 할인 가능하다.")
    @ParameterizedTest
    @CsvSource(value = {"10,true", "9,false"})
    void support(int amount, boolean expect) {
        //given
        Menu menu = new Menu(1, "test", Category.CHICKEN, 10000);
        OrderLine orderLine = new OrderLine(menu, amount);
        Orders orders = new Orders(Arrays.asList(orderLine));

        //when
        AmountDiscountPolicy amountDiscountPolicy = new ChickenAmountDiscountPolicy();
        boolean actual = amountDiscountPolicy.support(orders);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("치킨 메뉴 10개 마다 10000원씩 할인해 준다.")
    @ParameterizedTest
    @CsvSource(value = {"10,100000", "19,199000", "20,200000"})
    void discount(int amount, int expect) {
        //given
        Menu menu = new Menu(1, "test", Category.CHICKEN, 11000);
        OrderLine orderLine = new OrderLine(menu, amount);
        Orders orders = new Orders(Arrays.asList(orderLine));

        //when
        AmountDiscountPolicy amountDiscountPolicy = new ChickenAmountDiscountPolicy();
        int actual = amountDiscountPolicy.discount(orders);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}