import domain.Menu;
import domain.MenuRepository;
import domain.Table;
import domain.TableRepository;
import view.InputView;
import view.OutputView;

import java.util.List;

public class Application {
    public static void main(String[] args) {
        final List<Table> tables = TableRepository.tables();
        final List<Menu> menus = MenuRepository.menus();

        OutputView.printTables(tables);

        final int tableNumber = InputView.inputTableNumber();

        OutputView.printMenus(menus);
    }
}
