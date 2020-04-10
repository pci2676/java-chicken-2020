package domain.table;

import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.orders.OrderLine;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TableTest {

    @DisplayName("초기상태의 테이블은 주문이 비어있다.")
    @Test
    void name() {
        //given
        Table table = TableRepository.findByNumber(1L);

        //then
        assertThat(table.hasOrder()).isEqualTo(false);
    }

    @DisplayName("주문을 추가하면 테이블의 주문은 비어있지 않다.")
    @Test
    void name2() {
        //given
        Table table = TableRepository.findByNumber(1L);

        //when
        Menu menu = MenuRepository.findByNumber(1L);
        table.addOrder(new OrderLine(menu, 1));

        //then
        assertThat(table.hasOrder()).isEqualTo(true);
    }
}