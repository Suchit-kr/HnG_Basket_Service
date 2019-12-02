package com.hng.BasketService.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserLogin implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userId;
    private String userLoginId;
    private String currentPassword;
    private String userMobile;
    private String userAuthToken;
    private String userStatus;
    private long id;
    private java.sql.Timestamp createdStamp;
    private java.sql.Timestamp lastUpdatedStamp;

}
