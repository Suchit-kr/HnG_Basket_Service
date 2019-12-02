package com.hng.BasketService.exception;

import com.hng.BasketService.utility.ErrorCode;

/**
 * @author Suchit
 */

public class ProductException extends Exception {
    private ErrorCode errorCode;

    public ProductException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
