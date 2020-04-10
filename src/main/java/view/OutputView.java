package view;

import domain.menu.Menu;
import domain.orders.OrderLine;
import domain.payment.PaymentMethod;
import domain.table.Table;

import java.util.List;

public class OutputView {
    private static final String TOP_LINE = "┌ ─ ┐";
    private static final String TABLE_FORMAT = "| %s |";
    private static final String BOTTOM_LINE = "└ %s ┘";

    public static void printTables(final List<Table> tables) {
        System.out.println("## 테이블 목록");
        final int size = tables.size();
        printTopLine(size);
        printTableNumbers(tables);
        printBottomLine(size, tables);
    }

    public static void printMenus(final List<Menu> menus) {
        for (final Menu menu : menus) {
            System.out.println(menu);
        }
    }

    private static void printTopLine(final int count) {
        for (int index = 0; index < count; index++) {
            System.out.print(TOP_LINE);
        }
        System.out.println();
    }

    private static void printBottomLine(final int count, List<Table> tables) {
        for (int index = 0; index < count; index++) {
            System.out.print(String.format(BOTTOM_LINE, getOrderState(tables.get(index))));
        }
        System.out.println();
    }

    private static String getOrderState(Table table) {
        if (table.hasOrder()) {
            return "₩";
        }
        return "-";
    }

    private static void printTableNumbers(final List<Table> tables) {
        for (final Table table : tables) {
            System.out.printf(TABLE_FORMAT, table.getNumber());
        }
        System.out.println();
    }

    public static void printOrderLines(List<OrderLine> orderLines) {
        System.out.println("## 주문 내역");
        System.out.println("메누 수량 금액");
        for (OrderLine orderLine : orderLines) {
            System.out.println(orderLine);
        }
    }

    public static void printTotalPrice(Table table, PaymentMethod paymentMethod) {
        System.out.println("## 최종 결제 금액");
        System.out.println(table.calculatePrice(paymentMethod));
    }
}
