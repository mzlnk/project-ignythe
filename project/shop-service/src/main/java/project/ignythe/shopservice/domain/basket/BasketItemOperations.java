package project.ignythe.shopservice.domain.basket;

public interface BasketItemOperations {

    record BasketItemListDetails(Long basketId) {
    }

    record BasketItemGetDetails(Long basketId, Long basketItemId) {
    }

    record BasketItemCreateDetails(Long basketId, Long itemId) {
    }

    record BasketItemDeleteDetails(Long basketId, Long basketItemId) {

    }

}
