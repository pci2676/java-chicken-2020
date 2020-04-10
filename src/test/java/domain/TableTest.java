package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TableTest {

    @DisplayName("단일 메뉴 주문 하기")
    @Test
    void name0() {
        //given
        Table table = TableRepository.findByNumber(1L);

        //when

        //then
    }

    @DisplayName("복합 메뉴 주문 하기")
    @Test
    void name() {
        //given

        //when

        //then
    }

    @DisplayName("단일 메뉴의 주문 수량이 0개 이하인 경우 Exception")
    @Test
    void name1() {
        //given

        //when

        //then
    }

    @DisplayName("단일 메뉴의 주문 수량이 99개를 초과하면 Exception")
    @Test
    void name2() {
        //given

        //when

        //then
    }

    @DisplayName("기존 주문이 존재하던 테이블 주문을 추가하여 단일 메뉴의 갯수가 99개를 넘어가면 Exception 발생")
    @Test
    void name3() {
        //given

        //when

        //then
    }
}