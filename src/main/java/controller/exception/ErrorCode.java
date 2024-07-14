package controller.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    PRODUCT_NOT_FOUND(404, "Product not found"),
    UPDATE_PRODUCT_FAILED(1000, "Update product failed"),
    UPDATE_PRODUCT_SUCCESS(200, "Update product success"),
    SIZE_ERROR(1001, "Size error"),
    COLOR_ERROR(1002, "Color error"),
    CATEGORY_ERROR(1003, "Category error"),
    MISSING_REQUEST(1004, "Empty request"),
    PARAMETER_ERROR(1005, "Parameter error"),
    ;
    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
