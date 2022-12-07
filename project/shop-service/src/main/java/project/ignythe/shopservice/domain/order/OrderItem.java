package project.ignythe.shopservice.domain.order;

import lombok.*;
import project.ignythe.shopservice.domain.item.Item;
import project.ignythe.shopservice.domain.item.UnitType;

import javax.persistence.*;

import java.math.BigDecimal;

import static lombok.AccessLevel.PACKAGE;

@Entity
@Getter
@Setter(PACKAGE)
@Builder
@Table(name = "ORDER_ITEMS")
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    private static final String SEQUENCE = "ORDER_ITEMS_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, initialValue = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REF_ITEM_ID", nullable = false)
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "UNIT_TYPE")
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @Column(name = "AMOUNT")
    private Long amount;

}
