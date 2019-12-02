package com.hng.BasketService.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BasketItemList {

    private List<Item> itemList;
    private double totalBill;
    private long itemCount;

}
