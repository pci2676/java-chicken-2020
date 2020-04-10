package domain;

import java.util.List;
import java.util.Objects;

public class Orders {
    private final List<OrderLine> orderLines;

    public Orders(final List<OrderLine> OrderLines) {
        this.orderLines = OrderLines;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Orders orders = (Orders) o;
        return Objects.equals(orderLines, orders.orderLines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderLines);
    }

    public void addOrderLine(final OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }
}
