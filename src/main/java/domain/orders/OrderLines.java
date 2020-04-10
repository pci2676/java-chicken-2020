package domain.orders;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

public class OrderLines {
    private final List<OrderLine> orderLines;

    public OrderLines(final List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }

    public void add(final OrderLine orderLine) {
        if (hasSameOrderLine(orderLine)) {
            OrderLine givenOrderLine = findSameOrderLine(orderLine);
            givenOrderLine.add(orderLine);
            return;
        }
        this.orderLines.add(orderLine);
    }

    private OrderLine findSameOrderLine(OrderLine orderLine) {
        return this.orderLines.stream()
                .filter(aOrderLine -> aOrderLine.equals(orderLine))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("존재하지 않는 주문(%s)입니다.", orderLine.getMenuName())));
    }

    private boolean hasSameOrderLine(OrderLine orderLine) {
        return this.orderLines.stream()
                .anyMatch(aOrderLine -> aOrderLine.equals(orderLine));
    }

    public boolean hasOrderLine() {
        return !this.orderLines.isEmpty();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final OrderLines that = (OrderLines) o;
        return Objects.equals(orderLines, that.orderLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderLines);
    }
}
