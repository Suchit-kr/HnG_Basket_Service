package com.hng.BasketService.exception;

import com.hng.BasketService.dto.CustomResponseEntity;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

/**
 * @author Suchit
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity handleAllException(Exception e) {
        CustomResponseEntity customResponseEntity = buildResponse(e);
        return new ResponseEntity(customResponseEntity, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {ProductException.class})
    public ResponseEntity handlePProductNotFoundException(ProductException e) {
        CustomResponseEntity customResponseEntity = buildResponse(e);
        return new ResponseEntity(customResponseEntity, e.getErrorCode().getValue());
    }

    @ExceptionHandler(value = {UserException.class})
    public ResponseEntity handleUserNotFoundException(UserException e) {
        CustomResponseEntity customResponseEntity = buildResponse(e);
        return new ResponseEntity(customResponseEntity, e.getErrorCode().getValue());
    }

    @ExceptionHandler(value = SQLException.class)
    public ResponseEntity handleSqlException(SQLException e) {
        CustomResponseEntity customResponseEntity = buildResponse(e);
        return new ResponseEntity(customResponseEntity, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = BasketItemException.class)
    public ResponseEntity handleBasketItemException(BasketItemException e) {
        CustomResponseEntity customResponseEntity = buildResponse(e);
        return new ResponseEntity(customResponseEntity, e.getErrorCode().getValue());
    }

    private CustomResponseEntity buildResponse(Exception e) {
        LOGGER.error(ExceptionUtils.getMessage(e));
        CustomResponseEntity customResponseEntity = CustomResponseEntity.builder().message(e.getMessage()).build();
        return customResponseEntity;
    }


}
