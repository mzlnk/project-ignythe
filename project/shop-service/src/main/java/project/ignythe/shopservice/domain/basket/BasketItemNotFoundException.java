package project.ignythe.shopservice.domain.basket;

import java.util.function.Supplier;

public class BasketItemNotFoundException extends RuntimeException {

    public BasketItemNotFoundException(Long basketId, Long basketItemId) {
        super("Basket item with id '%s' for basket with id '%s' not found".formatted(basketId, basketItemId));
    }

    public static Supplier<BasketItemNotFoundException> basketItemNotFound(Long basketId, Long basketItemId) {
        return () -> new BasketItemNotFoundException(basketId, basketItemId);

    }

}
