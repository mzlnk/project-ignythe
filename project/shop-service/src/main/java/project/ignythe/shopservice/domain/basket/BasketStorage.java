package project.ignythe.shopservice.domain.basket;

import project.ignythe.shopservice.api.basket.BasketCreateRequest;
import project.ignythe.shopservice.api.basket.BasketItemCreateRequest;
import project.ignythe.shopservice.domain.item.ItemStorage;

public class BasketStorage {

    private final BasketRepository basketRepository;
    private final BasketItemRepository basketItemRepository;
    private final ItemStorage itemStorage;

    public BasketStorage(BasketRepository basketRepository, BasketItemRepository basketItemRepository, ItemStorage itemStorage) {
        this.basketRepository = basketRepository;
        this.basketItemRepository = basketItemRepository;
        this.itemStorage = itemStorage;
    }

    public Basket getById(Long id) {
        return basketRepository.findById(id)
                .orElseThrow(() -> new BasketNotFoundException(id));
    }

    public Basket createBasket(BasketCreateRequest createRequest) {
        var basket = Basket.builder()
                .name(createRequest.name())
                .build();
        return basketRepository.save(basket);
    }

    public BasketItem createBasketItem(Long basketId, BasketItemCreateRequest createRequest) {
        var item = itemStorage.getById(createRequest.itemId());
        var basket = getById(basketId);

        var basketItem = BasketItem.builder()
                .item(item)
                .basket(basket)
                .build();

        return basketItemRepository.save(basketItem);
    }

}
