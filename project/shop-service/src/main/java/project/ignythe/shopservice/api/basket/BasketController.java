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

@RestController
@RequestMapping("/baskets")
class BasketController {

    private final BasketStorage basketStorage;
    private final BasketMapper basketMapper;

    BasketController(BasketStorage basketStorage, BasketMapper basketMapper) {
        this.basketStorage = basketStorage;
        this.basketMapper = basketMapper;
    }

    @GetMapping("/{basketId}")
    ResponseEntity<BasketResponse> get(@PathVariable Long basketId) {
        var getDetails = basketMapper.toBasketGetDetails(basketId);
        var basket = basketStorage.getBasketById(getDetails);

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
    ResponseEntity<BasketResponse> create(@RequestBody BasketCreateRequest createRequest) {
        var createDetails = basketMapper.toBasketCreateDetails(createRequest);
        var createdBasket = basketStorage.createBasket(createDetails);

        return new ResponseEntity<>(basketMapper.toBasketResponse(createdBasket), HttpStatus.CREATED);
    }

}
