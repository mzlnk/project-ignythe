package project.ignythe.shopservice.domain.item;

import java.util.UUID;

public record Item(UUID id, String name, int amount) {
}
