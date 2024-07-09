package services.state;

public enum ReviewState {
    VISIBLE(true),
    HIDE(false);

    private final Boolean value;

    public Boolean getValue() {
        return value;
    }

    private ReviewState(Boolean value) {
        this.value = value;
    }

}
