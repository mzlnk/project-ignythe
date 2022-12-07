package project.ignythe.shopservice.domain.basket;

import project.ignythe.shopservice.domain.item.ItemStorage;

import java.util.List;

import static project.ignythe.shopservice.domain.basket.BasketItemNotFoundException.basketItemNotFound;
import static project.ignythe.shopservice.domain.basket.BasketItemOperations.*;
import static project.ignythe.shopservice.domain.basket.BasketOperations.*;

public class BasketStorage {

    private final BasketRepository basketRepository;
    private final BasketItemRepository basketItemRepository;
    private final ItemStorage itemStorage;

    BasketStorage(BasketRepository basketRepository,
                         BasketItemRepository basketItemRepository,
                         ItemStorage itemStorage) {
        this.basketRepository = basketRepository;
        this.basketItemRepository = basketItemRepository;
        this.itemStorage = itemStorage;
    }

    public List<Basket> listBaskets() {
        return basketRepository.findAll();
    }

    public Basket getBasketById(BasketGetDetails getDetails) {
        return basketRepository.findById(getDetails.basketId())
                .orElseThrow(() -> new BasketNotFoundException(getDetails.basketId()));
    }

    public Basket createBasket(BasketCreateDetails createDetails) {
        var basket = Basket.builder()
                .name(createDetails.name())
                .build();
        return basketRepository.save(basket);
    }

    public List<BasketItem> listBasketItems(BasketItemListDetails listDetails) {
        return getBasketById(new BasketGetDetails(listDetails.basketId()))
                .getBasketItems()
                .stream()
                .toList();
    }

    public BasketItem getBasketItemById(BasketItemGetDetails getDetails) {
        var basket = getBasketById(new BasketGetDetails(getDetails.basketId()));
        return basketItemRepository.findById(getDetails.basketItemId())
                .orElseThrow(basketItemNotFound(getDetails.basketId(), getDetails.basketItemId()));
    }

    public BasketItem createBasketItem(BasketItemCreateDetails createDetails) {
        var item = itemStorage.getById(createDetails.itemId());
        var basket = getBasketById(new BasketGetDetails(createDetails.basketId()));

        var basketItem = BasketItem.builder()
                .item(item)
                .basket(basket)
                .build();

        return basketItemRepository.save(basketItem);
    }

    public void deleteBasketItemById(BasketItemDeleteDetails deleteDetails) {
        var basket = getBasketById(new BasketGetDetails(deleteDetails.basketId()));
        basketItemRepository.deleteById(deleteDetails.basketItemId());
    }

}
