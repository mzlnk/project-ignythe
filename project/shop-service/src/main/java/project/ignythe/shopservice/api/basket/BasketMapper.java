package project.ignythe.shopservice.api.basket;

import project.ignythe.shopservice.domain.basket.Basket;
import project.ignythe.shopservice.domain.basket.BasketItem;

import static project.ignythe.shopservice.api.basket.BasketResponse.BasketItemDetails;
import static project.ignythe.shopservice.domain.basket.BasketOperations.BasketCreateDetails;
import static project.ignythe.shopservice.domain.basket.BasketOperations.BasketGetDetails;

class BasketMapper {

    BasketGetDetails toBasketGetDetails(Long basketId) {
        return new BasketGetDetails(basketId);
    }

    BasketCreateDetails toBasketCreateDetails(BasketCreateRequest createRequest) {
        return new BasketCreateDetails(createRequest.name());
    }

    BasketResponse toBasketResponse(Basket basket) {
        return new BasketResponse(
                basket.getId(),
                basket.getName(),
                basket.getBasketItems().stream()
                        .map(this::toBasketItemDetails)
                        .toList()
        );
    }

    private BasketItemDetails toBasketItemDetails(BasketItem basketItem) {
        return new BasketItemDetails(
                basketItem.getItem().getName(),
                basketItem.getItem().getDescription()
        );
    }


}
