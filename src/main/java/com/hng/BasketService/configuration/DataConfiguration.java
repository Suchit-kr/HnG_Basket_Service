package com.hng.BasketService.configuration;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;

//@Configuration
//public class DataConfiguration {
//
//    @Bean
//    @Lazy
//    public DataSource t1DataSource() {
//        return DataSourceBuilder
//                .create()
//                .driverClassName("oracle.jdbc.OracleDriver")
//                .url("jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=TCP)(HOST=ict0000037-scan-oravip.dci.bt.com)(PORT=61901))(CONNECT_DATA=(SERVICE_NAME=SQET1_ANY)))")
//                .username("SQE_USER_MGMT")
//                .password("SQE_USER_MGMT")
//                .build();
//    }
//}
