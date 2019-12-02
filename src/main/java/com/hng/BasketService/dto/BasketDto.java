package com.hng.BasketService.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * @author Suchit
 */

@Builder
@Getter
public class BasketDto {

    private long itemCount;
    private Double grandTotal;
    private List<Item> basketItemGroupList;

}
