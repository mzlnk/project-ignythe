package project.ignythe.shopservice.api.basket;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ignythe.shopservice.domain.basket.BasketItemOperations;
import project.ignythe.shopservice.domain.basket.BasketStorage;

import java.util.List;

import static org.springframework.http.HttpStatus.*;
import static project.ignythe.shopservice.domain.basket.BasketItemOperations.*;

@RestController
@RequestMapping("/baskets/{basketId}/items")
class BasketItemController {

    private final BasketStorage basketStorage;
    private final BasketItemMapper basketItemMapper;

    BasketItemController(BasketStorage basketStorage, BasketMapper basketMapper, BasketItemMapper basketItemMappper) {
        this.basketStorage = basketStorage;
        this.basketItemMapper = basketItemMappper;
    }

    @GetMapping
    ResponseEntity<List<BasketItemResponse>> list(@PathVariable Long basketId) {
        var listDetails = new BasketItemListDetails(basketId);
        var items = basketStorage.listBasketItems(listDetails).stream()
                .map(basketItemMapper::toBasketItemResponse)
                .toList();

        return ResponseEntity.ok(items);
    }

    @GetMapping("/{basketItemId}")
    ResponseEntity<BasketItemResponse> get(@PathVariable Long basketId,
                                           @PathVariable Long basketItemId) {
        var getDetails = new BasketItemGetDetails(basketId, basketItemId);
        var item = basketStorage.getBasketItemById(getDetails);

        return ResponseEntity.ok(basketItemMapper.toBasketItemResponse(item));
    }

    @PostMapping
    ResponseEntity<BasketItemResponse> create(@PathVariable Long basketId,
                                              @RequestBody BasketItemCreateRequest createRequest) {
        var createDetails = new BasketItemCreateDetails(basketId, createRequest.itemId());
        var createdItem = basketStorage.createBasketItem(createDetails);

        return new ResponseEntity<>(basketItemMapper.toBasketItemResponse(createdItem), CREATED);
    }
}
