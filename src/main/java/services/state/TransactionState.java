package services.state;

public enum TransactionState {
    UN_PAID(1),
    PROCESSING(2),
    PAID(3);

    private final int value;

    TransactionState(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static TransactionState getByValue(int value) {
        for (TransactionState state : TransactionState.values()) {
            if (state.getValue() == value) {
                return state;
            }
        }
        return null;
    }
}
