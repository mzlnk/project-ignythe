package project.ignythe.shopservice.domain.item;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(Long id) {
        super("Item with id '%s' not found".formatted(id));
    }

}
