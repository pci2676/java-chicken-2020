package domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class MenuRepositoryTest {

    @DisplayName("메뉴 번호로 메뉴 찾기")
    @Test
    void findByNumber() {
        //given
        Integer number = 1;

        //when
        Menu actual = MenuRepository.findByNumber(number);

        //then
        assertThat(actual).isEqualTo(new Menu(1, "후라이드", Category.CHICKEN, 16_000));
    }

    @DisplayName("존재하지 않는 메뉴번호로 조회시 Exception")
    @Test
    void name() {
        //given
        Integer number = -1;

        //then
        assertThatThrownBy(() -> MenuRepository.findByNumber(number))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("%d는 존재하지 않는 메뉴 번호입니다.", number);
    }
}