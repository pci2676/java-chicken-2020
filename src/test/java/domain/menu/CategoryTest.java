package domain.menu;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class CategoryTest {

    @DisplayName("치킨인지 아닌지 확인")
    @ParameterizedTest
    @CsvSource(value = {"CHICKEN,true", "BEVERAGE,false"})
    void name(Category category, boolean expect) {
        //when
        boolean actual = category.isChicken();

        //then
        assertThat(actual).isEqualTo(expect);
    }
}