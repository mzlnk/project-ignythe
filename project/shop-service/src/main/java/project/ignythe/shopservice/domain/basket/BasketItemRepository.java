package project.ignythe.shopservice.domain.basket;

import org.springframework.data.jpa.repository.JpaRepository;

interface BasketItemRepository extends JpaRepository<BasketItem, Long> {
}
