package project.ignythe.shopservice.domain.item;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@Table(name = "ITEMS")
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    private static final String SEQUENCE = "ITEMS_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, initialValue = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "UNIT_PRICE")
    private BigDecimal unitPrice;

    @Column(name = "UNIT_TYPE")
    @Enumerated(EnumType.STRING)
    private UnitType unitType;

}
