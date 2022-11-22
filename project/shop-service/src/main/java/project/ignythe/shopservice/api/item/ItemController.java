package project.ignythe.shopservice.api.item;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ignythe.shopservice.domain.item.Item;
import project.ignythe.shopservice.domain.item.ItemService;

import java.util.List;

@RestController
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<Item>> list() {
        var items = itemService.list();
        return ResponseEntity.ok(items);
    }

    @PostMapping
    public ResponseEntity<Item> create(@RequestBody ItemCreateRequest createRequest) {
        var createdItem = itemService.create(createRequest);
        return new ResponseEntity<>(createdItem, HttpStatus.CREATED);
    }

}
