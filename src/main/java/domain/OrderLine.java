package domain;

import java.util.Objects;

public class OrderLine {

    private static final int MINIMUM_ORDER_AMOUNT = 0;
    private static final int MAXIMUM_ORDER_AMOUNT = 99;
    private final Menu menu;
    private int amount;

    public OrderLine(final Menu menu, final int amount) {
        validateAmount(amount);
        this.menu = menu;
        this.amount = amount;
    }

    private void validateAmount(final int amount) {
        if (amount <= MINIMUM_ORDER_AMOUNT) {
            throw new IllegalArgumentException(String.format("주문 수량 : %d%s메뉴의 주문 갯수는 0개 이상이어야 합니다.", amount, System.lineSeparator()));
        }
        if (amount > MAXIMUM_ORDER_AMOUNT) {
            throw new IllegalArgumentException(String.format("주문 수량 : %d%s메뉴의 주문 갯수는 99개 이하여야 합니다.", amount, System.lineSeparator()));
        }
    }

    public void add(final OrderLine orderLine) {
        checkSameMenu(orderLine);
        checkMaximumOver(orderLine.amount);
        this.amount += orderLine.amount;
    }

    private void checkSameMenu(final OrderLine orderLine) {
        if (!this.menu.equals(orderLine.menu)) {
            throw new IllegalArgumentException(String.format("동일 메뉴에 대해서만 갯수를 추가할 수 있습니다.%s기존 메뉴 : %s, 추가시도 메뉴 : %s", System.lineSeparator(), this.getMenuName(), orderLine.getMenuName()));
        }
    }

    private void checkMaximumOver(final int addAmount) {
        if (this.amount + addAmount > MAXIMUM_ORDER_AMOUNT) {
            throw new IllegalStateException(String.format("단일 메뉴는 99이상 주문 할 수 없습니다.%s현재 갯수 : %d, 추가 갯수 : %d", System.lineSeparator(), this.amount, addAmount));
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
        return amount == orderLine.amount &&
                Objects.equals(menu, orderLine.menu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menu, amount);
    }
}
