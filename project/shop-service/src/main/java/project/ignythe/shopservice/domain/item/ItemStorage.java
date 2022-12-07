package project.ignythe.shopservice.domain.item;

import java.util.List;

public class ItemStorage {

    private final ItemRepository itemRepository;

    ItemStorage(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> list() {
        return itemRepository.findAll();
    }

    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Item create(ItemCreateDetails createDetails) {
        Item item = Item.builder()
                .name(createDetails.name())
                .description(createDetails.description())
                .unitPrice(createDetails.unitPrice())
                .unitType(createDetails.unitType())
                .build();

        return itemRepository.save(item);
    }

}
