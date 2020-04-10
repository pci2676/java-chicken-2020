package domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class MenuTest {

    @DisplayName("메뉴가 보유중인 번호인지 확인하기")
    @ParameterizedTest
    @CsvSource(value = {"1,true", "2,false"})
    void equalNumber(Integer number, boolean expect) {
        //given
        Menu menu = MenuRepository.findByNumber(1);

        //when
        boolean actual = menu.equalNumber(number);

        //then
        assertThat(actual).isEqualTo(expect);
    }

    @DisplayName("현재 메뉴의 가격에 갯수만큼 곱한 가격을 반환한다.")
    @Test
    void calculatePrice() {
        //given
        int defaultPrice = 1000;
        Menu menu = new Menu(1, "test", Category.CHICKEN, defaultPrice);
        int amount = 2;

        //when
        int price = menu.calculatePrice(amount);
        int expect = defaultPrice * amount;

        //then
        assertThat(price).isEqualTo(expect);
    }

    @DisplayName("치킨메뉴인지 아닌지 확인")
    @ParameterizedTest
    @CsvSource(value = {"CHICKEN,true", "BEVERAGE,false"})
    void name(Category category, boolean expect) {
        //given
        Menu menu = new Menu(1, "test", category, 1000);

        //when
        boolean actual = menu.isChicken();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}