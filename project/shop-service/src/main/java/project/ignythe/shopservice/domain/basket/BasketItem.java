package project.ignythe.shopservice.domain.basket;

import lombok.*;
import project.ignythe.shopservice.domain.item.Item;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@Table(name = "BASKET_ITEMS")
@NoArgsConstructor
@AllArgsConstructor
public class BasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Item item;

    @ManyToOne
    private Basket basket;

}
