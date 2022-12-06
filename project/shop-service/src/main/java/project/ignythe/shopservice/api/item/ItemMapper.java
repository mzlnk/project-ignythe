package project.ignythe.shopservice.api.item;

import project.ignythe.shopservice.domain.item.Item;
import project.ignythe.shopservice.domain.item.ItemCreateDetails;

class ItemMapper {

    ItemResponse toItemResponse(Item item) {
        return new ItemResponse(
                item.getId(),
                item.getName(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getUnitType()
        );
    }

    ItemCreateDetails toItemCreateDetails(ItemCreateRequest createRequest) {
        return new ItemCreateDetails(
                createRequest.name(),
                createRequest.description(),
                createRequest.unitPrice(),
                createRequest.unitType()
        );
    }

}
