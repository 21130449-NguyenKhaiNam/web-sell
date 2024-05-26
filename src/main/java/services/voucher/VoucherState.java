package services.voucher;

public enum VoucherState {
    CAN_APPLY("1"),
    NOT_FOUND("2"),
    EMPTY_AVAILABLE_TURN("3"),
    EXPIRED("4"),
    CAN_NOT_APPLY("5"),
    PRODUCT("PRODUCT"),
    CATEGORY("CATEGORY");

    private String value;

    private VoucherState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
