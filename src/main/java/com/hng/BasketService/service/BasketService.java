package com.hng.BasketService.service;

import com.hng.BasketService.dto.CustomResponseEntity;
import com.hng.BasketService.exception.BasketItemException;
import com.hng.BasketService.exception.ProductException;

import java.sql.SQLException;


public interface BasketService {
    public CustomResponseEntity addProductToBasket(String productId, Long userId, Long quantity) throws ProductException, SQLException;

    public CustomResponseEntity deleteProductFromBasket(String productId,Long userId,Long quantity) throws ProductException, SQLException, BasketItemException;

    public CustomResponseEntity getBasket(long userId) throws SQLException;
}
