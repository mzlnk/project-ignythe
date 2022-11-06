package project.ignythe.shopservice.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

}
