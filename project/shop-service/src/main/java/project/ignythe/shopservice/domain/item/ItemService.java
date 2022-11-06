package project.ignythe.shopservice.domain.item;

import java.util.List;
import java.util.UUID;

public class ItemService {

    private final List<Item> items;

    public ItemService() {
        this.items = initializeItems();
    }

    public List<Item> list() {
        return items;
    }

    private List<Item> initializeItems() {
        return List.of(
                new Item(UUID.randomUUID(), "Apple", 10),
                new Item(UUID.randomUUID(), "Orange", 20),
                new Item(UUID.randomUUID(), "Pear", 35)
        );
    }
}
