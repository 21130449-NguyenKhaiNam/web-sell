package services.state;

public enum OrderState {
    PENDING(1),
    CONFIRMED(2),
    DELIVERY(3),
    COMPLETED(4),
    CANCELLED(5);

    private final int value;

    OrderState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static OrderState getByValue(int value) {
        for (OrderState state : OrderState.values()) {
            if (state.getValue() == value) {
                return state;
            }
        }
        return null;
    }
}
