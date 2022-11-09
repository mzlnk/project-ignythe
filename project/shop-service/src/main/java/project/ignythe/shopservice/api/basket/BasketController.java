package project.ignythe.shopservice.api.basket;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ignythe.shopservice.domain.basket.Basket;
import project.ignythe.shopservice.domain.basket.BasketItem;
import project.ignythe.shopservice.domain.basket.BasketStorage;

import java.util.Collection;

@RestController
@RequestMapping("/baskets")
public class BasketController {

    private final BasketStorage basketStorage;

    public BasketController(BasketStorage basketStorage) {
        this.basketStorage = basketStorage;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Basket> get(@PathVariable Long id) {
        var basket = basketStorage.getById(id);
        return ResponseEntity.ok(basket);
    }

    @PostMapping
    public ResponseEntity<Basket> createItem(@RequestBody BasketCreateRequest createRequest) {
        var createdBasket = basketStorage.createBasket(createRequest);
        return new ResponseEntity<>(createdBasket, HttpStatus.CREATED);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<Collection<BasketItem>> listItems(@PathVariable Long id) {
        var items = basketStorage.getById(id).getBasketItems();
        return ResponseEntity.ok(items);
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<BasketItem> createItem(@PathVariable(name = "id") Long basketId,
                                                 @RequestBody BasketItemCreateRequest createRequest) {
        var createdItem = basketStorage.createBasketItem(basketId, createRequest);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

}
