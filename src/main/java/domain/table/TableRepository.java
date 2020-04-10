package domain.table;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class TableRepository {
    private static final List<Table> tables = new ArrayList<>();

    static {
        tables.add(new Table(1L));
        tables.add(new Table(2L));
        tables.add(new Table(3L));
        tables.add(new Table(5L));
        tables.add(new Table(6L));
        tables.add(new Table(8L));
    }

    public static List<Table> tables() {
        return Collections.unmodifiableList(tables);
    }

    public static final Table findByNumber(Long number) {
        return tables().stream()
                .filter(table -> table.equalNumber(number))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(String.format("%d는 존재하지 않는 테이블 번호입니다.", number)));
    }
}
