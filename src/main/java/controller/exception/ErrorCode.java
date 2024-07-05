package controller.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    PRODUCT_NOT_FOUND(404, "Product not found"),
    ;
    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
