package com.hng.BasketService.utility;

import com.hng.BasketService.dto.BasketDto;
import com.hng.BasketService.model.HngBasket;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponseBuilder {

    @Autowired
    private ModelMapper modelMapper;

    public BasketDto buildBasket(HngBasket basket) {
        return (BasketDto) convertToDto(basket);
    }


    private Object convertToDto(Object object) {
        Object result=null;
        if(object instanceof HngBasket) {
             result =modelMapper.map(object, BasketDto.class);
        }
        return result;
    }
}
