package com.hng.BasketService.controller;


import com.hng.BasketService.dto.CustomResponseEntity;
import com.hng.BasketService.exception.ProductException;
import com.hng.BasketService.exception.UserException;
import com.hng.BasketService.service.BasketService;
import com.hng.BasketService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.sql.SQLException;

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

    @PutMapping("/{productId}/add/{quantity}")
    public ResponseEntity<Object> addProductToBasket(@NotNull @PathVariable(value = "productId") String productId,
                                                     @PathVariable(value = "quantity") Long quantity, @RequestHeader(value = "userId") Long userId) throws UserException, ProductException, SQLException {
        userService.findUserById(userId);
        CustomResponseEntity customResponseEntity = basketService.addProductToBasket(productId, userId, quantity);
        return new ResponseEntity(customResponseEntity, HttpStatus.OK);

    }

    @DeleteMapping("/{productId}/delete/{quantity}")
    public ResponseEntity<Object> deleteProductFromBasket(@NotNull @PathVariable(value = "productId") String productId,
                                                     @PathVariable(value = "quantity") Long quantity, @RequestHeader(value = "userId") Long userId) throws Exception {
        userService.findUserById(userId);
        CustomResponseEntity customResponseEntity = basketService.deleteProductFromBasket(productId, userId, quantity);
        return new ResponseEntity(customResponseEntity, HttpStatus.OK);

    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Object> getBasket(@RequestParam Long userId) throws UserException, SQLException {
        CustomResponseEntity customResponseEntity = basketService.getBasket(userId);
        return new ResponseEntity<>(customResponseEntity, HttpStatus.OK);
    }

}
