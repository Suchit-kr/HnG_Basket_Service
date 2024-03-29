package com.hng.BasketService.service;

import com.hng.BasketService.exception.UserException;
import com.hng.BasketService.model.UserLogin;

/**
 * @author Suchit
 */

public interface UserService {

    public UserLogin findUserById(Long id) throws UserException;
}
