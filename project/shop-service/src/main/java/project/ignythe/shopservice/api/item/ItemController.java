package project.ignythe.shopservice.api.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ignythe.shopservice.domain.item.ItemStorage;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/items")
class ItemController {

    private final ItemStorage itemStorage;
    private final ItemMapper itemMapper;

    ItemController(ItemStorage itemStorage, ItemMapper itemMapper) {
        this.itemStorage = itemStorage;
        this.itemMapper = itemMapper;
    }

    @GetMapping
    ResponseEntity<List<ItemResponse>> list() {
        var items = itemStorage.list().stream()
                .map(itemMapper::toItemResponse)
                .toList();

        return ResponseEntity.ok(items);
    }

    @GetMapping("/{itemId}")
    ResponseEntity<ItemResponse> get(@PathVariable Long itemId) {
        var item = itemStorage.getById(itemId);
        return ResponseEntity.ok(itemMapper.toItemResponse(item));
    }

    @PostMapping
    ResponseEntity<ItemResponse> create(@RequestBody @Valid ItemCreateRequest createRequest) {
        var createdItem = itemStorage.create(itemMapper.toItemCreateDetails(createRequest));
        return new ResponseEntity<>(itemMapper.toItemResponse(createdItem), HttpStatus.CREATED);
    }

}
