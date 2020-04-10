package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Orders {
    private final OrderLines orderLines;

    public Orders(final List<OrderLine> orderLines) {
        this.orderLines = new OrderLines(orderLines);
    }

    public static Orders empty() {
        return new Orders(new ArrayList<>());
    }

    public void add(final OrderLine orderLine) {
        this.orderLines.add(orderLine);
    }

    public boolean hasOrderLine() {
        return this.orderLines.hasOrderLine();
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
}
