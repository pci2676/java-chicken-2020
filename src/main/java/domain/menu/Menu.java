package domain.menu;

import java.util.Objects;

/**
 * 제약 조건
 * 1. 기본 생성자 추가금지
 * 2. 멤버변수 추가 금지
 * - 기존 멤버 변수 데이터 타입 변경 가능
 * 3. 필드의 접근제어자는 private 으로 유지
 */
public class Menu {
    private final Integer number;
    private final String name;
    private final Category category;
    private final int price;

    public Menu(final Integer number, final String name, final Category category, final int price) {
        this.number = number;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public boolean equalNumber(final Integer number) {
        return this.number.equals(number);
    }

    public int calculatePrice(final int amount) {
        return this.price * amount;
    }

    public boolean isChicken() {
        return category.isChicken();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Menu menu = (Menu) o;
        return price == menu.price &&
                Objects.equals(number, menu.number) &&
                Objects.equals(name, menu.name) &&
                category == menu.category;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, name, category, price);
    }

    @Override
    public String toString() {
        return category + " " + number + " - " + name + " : " + price + "원";
    }
}
