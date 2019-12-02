package com.hng.BasketService.service;

import com.hng.BasketService.dao.UserDao;
import com.hng.BasketService.exception.UserException;
import com.hng.BasketService.model.UserLogin;
import com.hng.BasketService.utility.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserLogin findUserById(Long userId) throws UserException {
        Optional<UserLogin> userLoginOptional = userDao.findById(userId);
        if (!userLoginOptional.isPresent())
            throw new UserException("No user available for the given Id : " + userId, ErrorCode.NOT_AVAILABLE);
        if (userLoginOptional.get().getUserStatus().equalsIgnoreCase("Inactive"))
            throw new UserException("User is available but not active : " + userId, ErrorCode.USER_NOT_AUTHORIZED);
        return userLoginOptional.get();
    }

}
