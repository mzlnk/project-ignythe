package project.ignythe.shopservice.domain.basket;

public class BasketNotFoundException extends RuntimeException {

    public BasketNotFoundException(Long id) {
        super("Basket with id '%s' not found".formatted(id));
    }

}
