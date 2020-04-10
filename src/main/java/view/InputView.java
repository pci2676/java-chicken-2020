package view;

import domain.table.Table;

import java.util.Scanner;

public class InputView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_COMMAND = 3;
    private static final int MIN_COMMAND = 1;

    public static int inputMainCommand() {
        System.out.println("## 메인화면");
        System.out.println("1. 주문하기");
        System.out.println("2. 결제하기");
        System.out.println("3. 프로그램 종료");
        String command = scanner.nextLine();
        try {
            int commandNumber = Integer.parseInt(command);
            checkCommandNumber(commandNumber);
            return commandNumber;
        } catch (IllegalArgumentException e) {
            System.out.println(String.format("%s : 잘못된 입력입니다.", command));
            return inputMainCommand();
        }
    }

    private static void checkCommandNumber(final int commandNumber) {
        if (commandNumber > MAX_COMMAND || commandNumber < MIN_COMMAND) {
            throw new IllegalArgumentException(String.format("존재하지 않는 커맨드(%d)입니다.", commandNumber));
        }
    }

    public static int inputTableNumber() {
        System.out.println("## 주문할 테이블을 선택하세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int inputMenuNumber() {
        System.out.println("## 등록할 메뉴를 선택하세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int inputMenuAmount() {
        System.out.println("## 메뉴의 수량을 입력하세요.");
        return Integer.parseInt(scanner.nextLine());
    }

    public static int inputPaymentMethod(Table table) {
        System.out.println(String.format("%d번 테이블의 결제를 진행합니다", table.getNumber()));
        System.out.println("## 신용카드는 1번, 현금은 2번");
        return Integer.parseInt(scanner.nextLine());
    }
}
