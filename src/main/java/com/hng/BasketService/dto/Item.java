package com.hng.BasketService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private long productId;
    private String productName;
    private double listPrice;
    private long quantity;

}
