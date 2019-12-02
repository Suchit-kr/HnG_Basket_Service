package com.hng.BasketService.dao;

import com.hng.BasketService.model.UserLogin;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Suchit
 */
public interface UserDao extends CrudRepository<UserLogin,Long> {
}
