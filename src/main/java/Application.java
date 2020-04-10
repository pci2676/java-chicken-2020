import domain.menu.Menu;
import domain.menu.MenuRepository;
import domain.orders.OrderLine;
import domain.payment.PaymentMethod;
import domain.table.Table;
import domain.table.TableRepository;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        final List<Table> tables = TableRepository.tables();
        final List<Menu> menus = MenuRepository.menus();

        int command = 0;
        while (command != 3) {
            command = InputView.inputMainCommand();
            if (command == 1) {
                OutputView.printTables(tables);
                int tableNumber = InputView.inputTableNumber();
                Table table = TableRepository.findByNumber(tableNumber);
                OutputView.printMenus(menus);
                int menuNumber = InputView.inputMenuNumber();
                int menuAmount = InputView.inputMenuAmount();
                OrderLine orderLine = new OrderLine(MenuRepository.findByNumber(menuNumber), menuAmount);
                table.addOrder(orderLine);
                continue;
            }
            if (command == 2) {
                OutputView.printTables(tables);
                int tableNumber = InputView.inputTableNumber();
                Table table = TableRepository.findByNumber(tableNumber);
                OutputView.printOrderLines(table.getOrders());
                PaymentMethod paymentMethod = PaymentMethod.findByNumber(InputView.inputPaymentMethod(table));
                OutputView.printTotalPrice(table, paymentMethod);
                table.clear();
            }
        }

    }
}
