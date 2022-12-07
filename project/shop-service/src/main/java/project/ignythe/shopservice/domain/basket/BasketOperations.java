package project.ignythe.shopservice.domain.basket;

public interface BasketOperations {

    record BasketGetDetails(Long basketId) {
    }

    record BasketCreateDetails(String name) {
    }

    record BasketDeleteDetails(Long basketId) {

    }
}
