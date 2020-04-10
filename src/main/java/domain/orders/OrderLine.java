package domain.orders;

import domain.menu.Menu;

import java.util.Objects;

public class OrderLine {
    private final Menu menu;
    private OrderAmount orderAmount;

    public OrderLine(final Menu menu, final int amount) {
        this.menu = menu;
        this.orderAmount = OrderAmount.of(amount);
    }

    public void add(final OrderLine orderLine) {
        checkSameMenu(orderLine);
        this.orderAmount = this.orderAmount.plus(orderLine.orderAmount);
    }

    private void checkSameMenu(final OrderLine orderLine) {
        if (!this.menu.equals(orderLine.menu)) {
            throw new IllegalArgumentException(String.format("동일 메뉴에 대해서만 갯수를 추가할 수 있습니다.%s기존 메뉴 : %s, 추가시도 메뉴 : %s", System.lineSeparator(), this.getMenuName(), orderLine.getMenuName()));
        }
    }

    public String getMenuName() {
        return this.menu.getName();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderLine orderLine = (OrderLine) o;
        return Objects.equals(menu, orderLine.menu) &&
                Objects.equals(orderAmount, orderLine.orderAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, orderAmount);
    }

    public int getPrice() {
        return this.menu.calculatePrice(this.orderAmount.getAmount());
    }
}
