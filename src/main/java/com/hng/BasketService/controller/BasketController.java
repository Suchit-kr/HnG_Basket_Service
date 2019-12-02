package com.hng.BasketService.controller;


import com.hng.BasketService.dto.CustomResponseEntity;
import com.hng.BasketService.exception.ProductException;
import com.hng.BasketService.exception.UserException;
import com.hng.BasketService.service.BasketService;
import com.hng.BasketService.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;

/**
 * @author Suchit
 * Resource handler for Basket related operations
 *
 */


@RestController
@RequestMapping("/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test() {
        return "Pass";
    }


    /**
     * Add Product to basket
     * @param productId
     * @param quantity
     * @param userId
     * @return
     * @throws UserException
     * @throws ProductException
     * @throws SQLException
     */
    @PutMapping(value = "/{productId}/add/{quantity}")
    @ApiOperation(value = "Add Product To Basket",response = CustomResponseEntity.class)
    public ResponseEntity<Object> addProductToBasket(@NotNull @PathVariable(value = "productId") @ApiParam(value = "ID of Product") String productId,
                                                     @PathVariable(value = "quantity") @ApiParam(value = "Quantity") Long quantity, @RequestHeader(value = "userId") @ApiParam(value = "ID of User") Long userId) throws UserException, ProductException, SQLException {
        userService.findUserById(userId);
        CustomResponseEntity customResponseEntity = basketService.addProductToBasket(productId, userId, quantity);
        return new ResponseEntity(customResponseEntity, HttpStatus.CREATED);

    }

    /**
     * Delete product from basket
     * @param productId
     * @param quantity
     * @param userId
     * @return
     * @throws Exception
     */
    @DeleteMapping("/{productId}/delete/{quantity}")
    @ApiOperation(value = "Delete Product from Basket",response = CustomResponseEntity.class)
    public ResponseEntity<Object> deleteProductFromBasket(@NotNull @PathVariable(value = "productId") @ApiParam(value = "ID of Product") String productId,
                                                     @PathVariable(value = "quantity") @ApiParam(value = "Quantity") Long quantity, @RequestHeader(value = "userId") @ApiParam(value = "ID of User") Long userId) throws Exception {
        userService.findUserById(userId);
        CustomResponseEntity customResponseEntity = basketService.deleteProductFromBasket(productId, userId, quantity);
        return new ResponseEntity(customResponseEntity, HttpStatus.OK);

    }

    /**
     * Retrive the whole Basket
     * @param userId
     * @return
     * @throws UserException
     * @throws SQLException
     */
    @GetMapping("/get/{id}")
    @ApiOperation(value = "Retrieve Basket",response = CustomResponseEntity.class)
    public ResponseEntity<Object> getBasket(@RequestParam @ApiParam(value = "ID of User") Long userId) throws UserException, SQLException {
        userService.findUserById(userId);
        CustomResponseEntity customResponseEntity = basketService.getBasket(userId);
        return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
    }

}
