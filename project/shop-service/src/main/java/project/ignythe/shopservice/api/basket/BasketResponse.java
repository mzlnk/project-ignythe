package project.ignythe.shopservice.api.basket;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Details of basket")
public record BasketResponse(@Schema(description = "Basket identifier", example = "1") Long id,
                             @Schema(description = "Basket name", example = "Apple") String name,
                             List<BasketItemDetails> items) {

    public record BasketItemDetails(String name,
                                    String description,
                                    Long amount) {
    }

}
