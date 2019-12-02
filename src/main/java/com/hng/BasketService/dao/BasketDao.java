package com.hng.BasketService.dao;

import com.hng.BasketService.model.HngBasket;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Suchit
 */
public interface BasketDao extends CrudRepository<HngBasket,Long> {

    public HngBasket getByUserId(long userId);


}
