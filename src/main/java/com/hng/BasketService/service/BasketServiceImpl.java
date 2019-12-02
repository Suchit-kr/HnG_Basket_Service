package com.hng.BasketService.service;

import com.hng.BasketService.dao.BasketDao;
import com.hng.BasketService.dao.BasketItemDao;
import com.hng.BasketService.dao.ProductDao;
import com.hng.BasketService.dto.BasketDto;
import com.hng.BasketService.dto.BasketItemList;
import com.hng.BasketService.dto.CustomResponseEntity;
import com.hng.BasketService.dto.Item;
import com.hng.BasketService.exception.BasketException;
import com.hng.BasketService.exception.BasketItemException;
import com.hng.BasketService.exception.ProductException;
import com.hng.BasketService.model.HngBasket;
import com.hng.BasketService.model.HngBasketItem;
import com.hng.BasketService.model.HngBasketItemPK;
import com.hng.BasketService.model.HngProductMaster;
import com.hng.BasketService.utility.CustomModelMapper;
import com.hng.BasketService.utility.ErrorCode;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BasketServiceImpl implements BasketService {

    private BasketDao basketDao;
    private ProductDao productDao;
    private BasketItemDao itemDao;

    @Autowired
    @Qualifier("modelMapper")
    private ModelMapper mapper;

    @Autowired
    @Qualifier("CustomModelMapper")
    private CustomModelMapper customModelMapper;


    @Autowired
    public BasketServiceImpl(BasketDao basketDao,  ProductDao productDao, BasketItemDao itemDao) {
        this.basketDao = basketDao;
        this.productDao = productDao;
        this.itemDao = itemDao;
    }

    @Transactional
    public CustomResponseEntity addProductToBasket(String productId, Long userId, Long quantity) throws ProductException, SQLException {
        HngBasketItemPK hngBasketItemPK = HngBasketItemPK.builder().basketId(userId).productId(productId).build();
        HngBasketItem item = getBasketItem(hngBasketItemPK);

        HngBasket userBasket = getBasketForUser(userId);
        if (item != null) {

            item.setQuantity(item.getQuantity() + quantity);
            itemDao.save(item);
            userBasket.setGrandTotal(userBasket.getGrandTotal() + (item.getListPrice() * quantity));
            userBasket.setNoOfItems(userBasket.getNoOfItems() + quantity);

        } else {
            HngProductMaster product = getProductById(productId);

            HngBasketItem basketItem = HngBasketItem.builder().id(hngBasketItemPK).quantity(quantity)
                    .mrpPrice(product.getMrpPrice()).listPrice(product.getListPrice()).productName(product.getProductTitle())
                    .lastUpdatedStamp(Timestamp.valueOf(LocalDateTime.now())).build();
            item = saveBasketItem(basketItem);
            userBasket.setGrandTotal(userBasket.getGrandTotal() + (item.getListPrice() * item.getQuantity()));
            userBasket.setNoOfItems(userBasket.getNoOfItems() + quantity);
        }
        saveBasket(userBasket);

        return responseBuilder(item,"Item added to the cart successfully");

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomResponseEntity deleteProductFromBasket(String productId, Long userId, Long quantity) throws ProductException, SQLException, BasketItemException {
        HngBasketItemPK hngBasketItemPK = HngBasketItemPK.builder().basketId(userId).productId(productId).build();
        HngBasketItem item = getBasketItem(hngBasketItemPK);

        HngBasket userBasket = getBasketForUser(userId);

        if (item == null) {
            throw new BasketItemException("No Basket Item available for deletion", ErrorCode.NOT_AVAILABLE);
        } else if (item.getQuantity() < quantity) {
            throw new BasketItemException("Provided quantity is more than available quantity", ErrorCode.BAD_REQUEST);
        } else if (item.getQuantity() - quantity != 0) {
            item.setQuantity(item.getQuantity() - quantity);
            item = saveBasketItem(item);
            userBasket.setGrandTotal(userBasket.getGrandTotal() - (item.getListPrice() * item.getQuantity()));
            userBasket.setNoOfItems(userBasket.getNoOfItems() - quantity);
        } else {
            deleteBasketItem(item);
            userBasket.setGrandTotal(userBasket.getGrandTotal() - (item.getListPrice() * item.getQuantity()));
            userBasket.setNoOfItems(userBasket.getNoOfItems() - quantity);
        }
        saveBasket(userBasket);

        return responseBuilder(item,"Item deleted from the cart successfully");

    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public CustomResponseEntity getBasket(long userId) throws SQLException {
        HngBasket basket = getBasketForUser(userId);
        List<HngBasketItem> itemList= getAllBasketItem(basket.getId());
        List<Item> items = customModelMapper.mapAll(itemList, Item.class);
        BasketDto basketDto = BasketDto.builder().basketItemGroupList(items).grandTotal(basket.getGrandTotal()).itemCount(items.size()).build();
        return responseBuilder(basketDto,"Basket retrieved successfully");
    }

    @Transactional(propagation = Propagation.REQUIRED)
     List<HngBasketItem> getAllBasketItem(long basketId) {
        return itemDao.getAllBasketItems(basketId);
    }


    @Transactional(propagation = Propagation.REQUIRED)
    HngBasket getBasketForUser(Long userId) throws SQLException {
        return basketDao.getByUserId(userId);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    HngBasketItem getBasketItem(HngBasketItemPK hngBasketItemPK) throws ProductException {
        Optional<HngBasketItem> itemOptional = itemDao.findById(hngBasketItemPK);
        if (!itemOptional.isPresent())
            return null;
        return itemOptional.get();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    HngProductMaster getProductById(String productId) throws ProductException {
        Optional<HngProductMaster> productOptional = productDao.findById(productId);
        if (!productOptional.isPresent())
            throw new ProductException("No product available for the given Id : " + productId, ErrorCode.NOT_AVAILABLE);
        return productOptional.get();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    HngBasketItem saveBasketItem(HngBasketItem item) {
        return itemDao.save(item);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    HngBasket saveBasket(HngBasket basket) {
        return basketDao.save(basket);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    HngBasket getBasketDetails(String basketId) throws BasketException {
        if (basketId == null)
            throw new BasketException("No Basket available for the user", ErrorCode.NOT_AVAILABLE);
        return null;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    void deleteBasketItem(HngBasketItem item) {
        itemDao.delete(item);
    }

    private CustomResponseEntity responseBuilder(Object item,String message) {
        if (item instanceof HngBasketItem) {
            Item dto = mapper.map(item, Item.class);
            List<Item> itemList = new ArrayList<>();
            itemList.add(dto);
            return CustomResponseEntity.builder().message(message).basketItemList(BasketItemList.builder().itemCount(1L).itemList(itemList).build()).build();
        }
        if (item instanceof HngBasket) {
            BasketDto basketDto = mapper.map(item, BasketDto.class);
           return CustomResponseEntity.builder().message(message).basketItemList(BasketItemList.builder().itemList((basketDto.getBasketItemGroupList())).build()).build();
        }if(item instanceof BasketDto){
            BasketDto basketDto = (BasketDto) item;
            return CustomResponseEntity.builder().message(message).basketItemList(BasketItemList.builder().itemList(((basketDto.getBasketItemGroupList()))).itemCount((basketDto.getBasketItemGroupList().size())).totalBill(basketDto.getGrandTotal()).build()).build();
        }
        return null;
    }

}
