package com.hng.BasketService.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomResponseEntity {
   private String message;
   private BasketItemList basketItemList;
}
