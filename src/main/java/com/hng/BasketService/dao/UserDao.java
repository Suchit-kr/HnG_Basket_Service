package com.hng.BasketService.dao;

import com.hng.BasketService.model.UserLogin;
import org.springframework.data.repository.CrudRepository;

public interface UserDao extends CrudRepository<UserLogin,Long> {
}
