package domain.table;

import domain.orders.OrderLine;
import domain.orders.Orders;

import java.util.Objects;

/**
 * 제약조건
 * 1. 기본 생성자 추가 금지
 * 2. 멤버 변수 추가시 접근 제어자 private
 */
public class Table {
    private final Long number;
    private final Orders orders = Orders.empty();

    public Table(final Long number) {
        this.number = number;
    }

    public boolean equalNumber(final Long number) {
        return this.number.equals(number);
    }

    public void addOrder(final OrderLine orderLine) {
        this.orders.add(orderLine);
    }

    public boolean hasOrder() {
        return this.orders.hasOrderLine();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Table table = (Table) o;
        return Objects.equals(number, table.number) &&
                Objects.equals(orders, table.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, orders);
    }
}
