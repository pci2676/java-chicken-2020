package domain.table;

import domain.menu.Category;
import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.orders.OrderLine;
import domain.payment.PaymentMethod;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TableTest {

    @DisplayName("초기상태의 테이블은 주문이 비어있다.")
    @Test
    void name() {
        //given
        Table table = TableRepository.findByNumber(1);

        //then
        assertThat(table.hasOrder()).isEqualTo(false);
    }

    @DisplayName("주문을 추가하면 테이블의 주문은 비어있지 않다.")
    @Test
    void name2() {
        //given
        Table table = TableRepository.findByNumber(1);

        //when
        Menu menu = MenuRepository.findByNumber(1);
        table.addOrder(new OrderLine(menu, 1));

        //then
        assertThat(table.hasOrder()).isEqualTo(true);
    }

    @DisplayName("주문내역이 존재하지 않는데 결제 시도시 Exception")
    @Test
    void name3() {
        //given
        Table table = new Table(1);

        //then
        assertThatThrownBy(() -> table.calculatePrice(PaymentMethod.CASH))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("주문내역이 존재하지 않습니다.");
    }

    @DisplayName("카드 치킨 10마리 결제시 10000원 할인된 가격 받기")
    @Test
    void name4() {
        //given
        Table table = new Table(1);
        Menu menu = new Menu(1, "test", Category.CHICKEN, 11000);
        OrderLine orderLine = new OrderLine(menu, 10);
        table.addOrder(orderLine);

        //when
        double actual = table.calculatePrice(PaymentMethod.CARD);

        //then
        assertThat(actual).isEqualTo(100000);
    }

    @DisplayName("결제정책에 따른 할인 된 가격 구하기")
    @ParameterizedTest
    @CsvSource(value = {"10,CARD,100000", "10,CASH,95000", "9,CARD,99000", "9,CASH,94050"})
    void name5(int amount, PaymentMethod paymentMethod, double expect) {
        //given
        Table table = new Table(1);
        Menu menu = new Menu(1, "test", Category.CHICKEN, 11000);
        OrderLine orderLine = new OrderLine(menu, amount);
        table.addOrder(orderLine);

        //when
        double actual = table.calculatePrice(paymentMethod);

        //then
        assertThat(actual).isEqualTo(expect);
    }
}