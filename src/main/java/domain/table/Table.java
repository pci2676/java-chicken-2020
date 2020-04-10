package domain.table;

import domain.discount.DiscountGroup;
import domain.orders.OrderLine;
import domain.orders.Orders;
import domain.payment.PaymentMethod;

import java.util.List;

/**
 * 제약조건
 * 1. 기본 생성자 추가 금지
 * 2. 멤버 변수 추가시 접근 제어자 private
 */
public class Table {
    private final Integer number;
    private Orders orders = Orders.empty();

    public Table(final Integer number) {
        this.number = number;
    }

    public boolean equalNumber(final Integer number) {
        return this.number.equals(number);
    }

    public void addOrder(final OrderLine orderLine) {
        this.orders.add(orderLine);
    }

    public boolean hasOrder() {
        return this.orders.hasOrderLine();
    }

    public double calculatePrice(PaymentMethod paymentMethod) {
        checkOrdersEmpty();
        return DiscountGroup.discount(this.orders, paymentMethod);
    }

    public void clear() {
        orders = Orders.empty();
    }

    public int getNumber() {
        return this.number;
    }

    public List<OrderLine> getOrders() {
        return orders.getOrderLines();
    }

    private void checkOrdersEmpty() {
        if (!hasOrder()) {
            throw new IllegalStateException("주문내역이 존재하지 않습니다.");
        }
    }
}
