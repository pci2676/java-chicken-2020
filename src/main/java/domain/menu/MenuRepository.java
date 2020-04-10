package domain.menu;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class MenuRepository {
    private static final List<Menu> menus = new ArrayList<>();

    static {
        menus.add(new Menu(1L, "후라이드", Category.CHICKEN, 16_000));
        menus.add(new Menu(2L, "양념치킨", Category.CHICKEN, 16_000));
        menus.add(new Menu(3L, "반반치킨", Category.CHICKEN, 16_000));
        menus.add(new Menu(4L, "통구이", Category.CHICKEN, 16_000));
        menus.add(new Menu(5L, "간장치킨", Category.CHICKEN, 17_000));
        menus.add(new Menu(6L, "순살치킨", Category.CHICKEN, 17_000));
        menus.add(new Menu(21L, "콜라", Category.BEVERAGE, 1_000));
        menus.add(new Menu(22L, "사이다", Category.BEVERAGE, 1_000));
    }

    public static List<Menu> menus() {
        return Collections.unmodifiableList(menus);
    }

    public static Menu findByNumber(Long number) {
        return menus().stream()
                .filter(menu -> menu.equalNumber(number))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("%d는 존재하지 않는 메뉴 번호입니다.", number)));
    }
}
