package domain.table;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TableRepositoryTest {

    @DisplayName("존재하는 테이블 번호로 테이블 가져오기")
    @Test
    void findByNumber() {
        //given
        Long number = 1L;

        //when
        Table actual = TableRepository.findByNumber(number);

        //then
        assertThat(actual).isEqualTo(new Table(number));
    }

    @DisplayName("존재하지 않는 테이블 조회시 Exception")
    @ParameterizedTest
    @CsvSource(value = {"0", "9"})
    void name(Long number) {
        assertThatThrownBy(() -> TableRepository.findByNumber(number))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("%d는 존재하지 않는 테이블 번호입니다.", number);
    }
}