package com.hng.BasketService.model;

import lombok.*;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.io.Serializable;

/**
 * @author Suchit
 */

@Entity
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HngBasketItem implements Serializable {
    @EmbeddedId
    private HngBasketItemPK id;
    private long quantity;
    private double mrpPrice;
    private double listPrice;
    private String productName;
    private java.sql.Timestamp lastUpdatedStamp;
    private java.sql.Timestamp createdStamp;


}
