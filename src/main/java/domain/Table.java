package domain;

import java.util.Objects;

/**
 * 제약조건
 * 1. 기본 생성자 추가 금지
 * 2. 멤버 변수 추가시 접근 제어자 private
 */
public class Table {
    private final Long number;

    public Table(final Long number) {
        this.number = number;
    }

    public boolean equalNumber(final Long number) {
        return this.number.equals(number);
    }

    @Override
    public String toString() {
        return Long.toString(number);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Table table = (Table) o;
        return Objects.equals(number, table.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
