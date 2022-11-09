package project.ignythe.shopservice.domain.item;

import project.ignythe.shopservice.api.item.ItemCreateRequest;

import java.util.List;

public class ItemService {

    private final ItemRepository itemRepository;

    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<Item> list() {
        return itemRepository.findAll();
    }

    public Item getById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(id));
    }

    public Item create(ItemCreateRequest createRequest) {
        Item item = Item.builder()
                .name(createRequest.name())
                .description(createRequest.description())
                .unitPrice(createRequest.unitPrice())
                .unitType(createRequest.unitType())
                .build();

        return itemRepository.save(item);
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }

}
