package com.hng.BasketService.utility;

import org.springframework.http.HttpStatus;

/**
 * @author Suchit
 */

public enum ErrorCode {
    NOT_AVAILABLE(HttpStatus.NOT_FOUND), USER_NOT_AUTHORIZED(HttpStatus.UNAUTHORIZED),BAD_REQUEST(HttpStatus.BAD_REQUEST);

    private HttpStatus errorCode;

    ErrorCode(HttpStatus errorCode) {
        this.errorCode = errorCode;
    }

    public HttpStatus getValue() {
        return this.errorCode;
    }
}
