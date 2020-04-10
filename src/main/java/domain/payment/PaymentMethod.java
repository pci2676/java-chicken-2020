package domain.payment;

import java.util.Arrays;

public enum PaymentMethod {
    CARD(1, "CARD"),
    CASH(2, "CASH");

    private final int methodNumber;
    private final String method;

    PaymentMethod(final int methodNumber, final String method) {
        this.methodNumber = methodNumber;
        this.method = method;
    }

    public static PaymentMethod findByNumber(final int number) {
        return Arrays.stream(values())
                .filter(method -> method.methodNumber == number)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("%d : 잘못된 결제수단을 선택하였습니다.", number)));
    }

    public boolean isCash() {
        return this == CASH;
    }
}
