package project.ignythe.shopservice.domain.basket;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@Table(name = "BASKETS")
@NoArgsConstructor
@AllArgsConstructor
public class Basket {

    private static final String SEQUENCE = "BASKETS_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, initialValue = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "basket")
    @Builder.Default
    private Set<BasketItem> basketItems = new HashSet<>();

}
