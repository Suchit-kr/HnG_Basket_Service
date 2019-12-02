package com.hng.BasketService.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class HngBasket implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  private long userId;
  private long noOfItems;
  private double grandTotal;
  private double deliveryCharge;
  private String appliedCouponCode;
  private double couponAmount;
  private java.sql.Timestamp lastUpdatedStamp;
  private java.sql.Timestamp createdStamp;



}
