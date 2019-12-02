package com.hng.BasketService.exception;

import com.hng.BasketService.utility.ErrorCode;

public class BasketItemException extends Exception {
    private ErrorCode errorCode;

    public BasketItemException(String message, ErrorCode errorCode){
        super(message);
        this.errorCode=errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}