package domain.orders;

import java.util.Objects;

public class OrderAmount {
    private static final int MINIMUM_ORDER_AMOUNT = 0;
    private static final int MAXIMUM_ORDER_AMOUNT = 99;

    private final int amount;

    private OrderAmount(final int amount) {
        validateAmount(amount);
        this.amount = amount;
    }

    public static OrderAmount of(final int amount) {
        return new OrderAmount(amount);
    }

    private void validateAmount(final int amount) {
        if (amount <= MINIMUM_ORDER_AMOUNT) {
            throw new IllegalArgumentException(String.format("주문 수량 : %d%s메뉴의 주문 갯수는 0개 이상이어야 합니다.", amount, System.lineSeparator()));
        }
        if (amount > MAXIMUM_ORDER_AMOUNT) {
            throw new IllegalArgumentException(String.format("주문 수량 : %d%s메뉴의 주문 갯수는 99개 이하여야 합니다.", amount, System.lineSeparator()));
        }
    }

    public OrderAmount plus(OrderAmount orderAmount) {
        checkMaximumOver(orderAmount.amount);
        return new OrderAmount(this.amount + orderAmount.amount);
    }


    private void checkMaximumOver(final int addAmount) {
        if (this.amount + addAmount > MAXIMUM_ORDER_AMOUNT) {
            throw new IllegalStateException(String.format("단일 메뉴는 99이상 주문 할 수 없습니다.%s현재 갯수 : %d, 추가 갯수 : %d", System.lineSeparator(), this.amount, addAmount));
        }
    }

    public int getAmount() {
        return amount;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderAmount that = (OrderAmount) o;
        return amount == that.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }
}
