package domain.payment;

public enum PaymentMethod {
    CARD(1, "CARD"),
    CASH(2, "CASH");

    private final int methodNumber;
    private final String method;

    PaymentMethod(final int methodNumber, final String method) {
        this.methodNumber = methodNumber;
        this.method = method;
    }

    public boolean isCash() {
        return this == CASH;
    }
}
