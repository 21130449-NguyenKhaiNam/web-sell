package controller.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {
    PRODUCT_NOT_FOUND(404, "Product not found"),
    UPDATE_FAILED(1000, "Update failed"),
    UPDATE_SUCCESS(200, "Update success"),
    CREATE_SUCCESS(200, "Create success"),
    SIZE_ERROR(1001, "Size error"),
    COLOR_ERROR(1002, "Color error"),
    CATEGORY_ERROR(1003, "Category error"),
    MISSING_REQUEST(1004, "Empty request"),
    ERROR_PARAM_REQUEST(1004, "Param not valid"),
    PARAMETER_ERROR(1005, "Parameter error"),
    IMAGE_ERROR(1005, "Image error"),
    ;
    private Integer code;
    private String message;

    ErrorCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
