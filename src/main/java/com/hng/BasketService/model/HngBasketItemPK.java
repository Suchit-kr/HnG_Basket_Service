package com.hng.BasketService.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@Builder
@NoArgsConstructor
@Embeddable
@ToString
public class HngBasketItemPK implements Serializable {

    @Column(name = "basket_id")
    protected long basketId;
    @Column(name = "product_id")
    protected String productId;

    public HngBasketItemPK(long basketId, String productId) {
        this.basketId = basketId;
        this.productId = productId;
    }

    public long getBasketId() {
        return basketId;
    }

    public String getProductId() {
        return productId;
    }
}
