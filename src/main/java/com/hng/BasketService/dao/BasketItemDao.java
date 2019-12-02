package com.hng.BasketService.dao;

import com.hng.BasketService.model.HngBasketItem;
import com.hng.BasketService.model.HngBasketItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * @author Suchit
 */
import java.util.List;

@Repository
public interface BasketItemDao extends JpaRepository<HngBasketItem, HngBasketItemPK> {

    @Query(value = "SELECT * FROM HNG_BASKET_ITEM WHERE BASKET_ID = ?1", nativeQuery = true)
    List<HngBasketItem> getAllBasketItems(long basketId);

}
