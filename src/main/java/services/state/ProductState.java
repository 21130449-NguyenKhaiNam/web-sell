package services.state;

import lombok.Getter;

@Getter
public enum ProductState {
    VISIBLE("1"),
    HIDE("0");

    private final String value;

    private ProductState(String value) {
        this.value = value;
    }

}
