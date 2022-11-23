package project.ignythe.shopservice.api.basket;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ignythe.shopservice.api.error.ErrorResponse;
import project.ignythe.shopservice.domain.basket.BasketStorage;

import java.util.Collection;

@RestController
@RequestMapping("/baskets")
public class BasketController {

    private final BasketStorage basketStorage;
    private final BasketMapper basketMapper;
    private final BasketItemMappper basketItemMappper;

    public BasketController(BasketStorage basketStorage, BasketMapper basketMapper, BasketItemMappper basketItemMappper) {
        this.basketStorage = basketStorage;
        this.basketMapper = basketMapper;
        this.basketItemMappper = basketItemMappper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<BasketResponse> get(@PathVariable Long id) {
        var basket = basketStorage.getById(id);
        return ResponseEntity.ok(basketMapper.toBasketResponse(basket));
    }

    @Operation(description = "Create a new basket")
    @ApiResponses({
            @ApiResponse(description = "Successfully created basket",
                    responseCode = "201",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BasketResponse.class))
            ),
            @ApiResponse(description = "Failed to create basket",
                    responseCode = "400",
                    content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))
            ),
            @ApiResponse(description = "Unauthorized attempt to create basket",
                    responseCode = "401",
                    content = @Content(mediaType = MediaType.APPLICATION_PROBLEM_JSON_VALUE, schema = @Schema(implementation = ErrorResponse.class))
            ),
    })
    @PostMapping
    public ResponseEntity<BasketResponse> create(@RequestBody BasketCreateRequest createRequest) {
        var createdBasket = basketStorage.createBasket(createRequest);
        return new ResponseEntity<>(basketMapper.toBasketResponse(createdBasket), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/items")
    public ResponseEntity<Collection<BasketItemResponse>> listItems(@PathVariable Long id) {
        var items = basketStorage.getById(id).getBasketItems()
                .stream()
                .map(basketItemMappper::toBasketItemResponse)
                .toList();

        return ResponseEntity.ok(items);
    }

    @PostMapping("/{id}/items")
    public ResponseEntity<BasketItemResponse> createItem(@PathVariable(name = "id") Long basketId,
                                                         @RequestBody BasketItemCreateRequest createRequest) {
        var createdItem = basketStorage.createBasketItem(basketId, createRequest);
        return new ResponseEntity<>(basketItemMappper.toBasketItemResponse(createdItem), HttpStatus.CREATED);
    }

}
