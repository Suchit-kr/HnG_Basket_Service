package com.hng.BasketService.dao;

import com.hng.BasketService.model.HngProductMaster;
import org.springframework.data.repository.CrudRepository;

/**
 * @author Suchit
 */
public interface ProductDao extends CrudRepository<HngProductMaster,String> {
}
