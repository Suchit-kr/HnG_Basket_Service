package com.hng.BasketService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

/**
 * @author Suchit
 */

@SpringBootApplication
public class BasketServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasketServiceApplication.class, args);
	}

	@Bean()
	@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
