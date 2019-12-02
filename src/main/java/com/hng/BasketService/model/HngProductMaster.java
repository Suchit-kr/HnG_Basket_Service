package com.hng.BasketService.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author Suchit
 */

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class HngProductMaster implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String productId;
    private long categoryId;
    private String categoryName;
    private String parentCategoryLink;
    private long brandId;
    private String brandName;
    private String productTitle;
    private java.sql.Timestamp createdStamp;
    private java.sql.Timestamp lastUpdatedStamp;
    private double mrpPrice;
    private double listPrice;

}
