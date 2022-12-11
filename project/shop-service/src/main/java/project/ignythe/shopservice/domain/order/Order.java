package project.ignythe.shopservice.domain.order;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static lombok.AccessLevel.PACKAGE;

@Entity
@Getter
@Setter(PACKAGE)
@Builder
@Table(name = "ORDERS")
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    private static final String SEQUENCE = "ORDERS_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, initialValue = 1)
    private Long id;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @Column(name = "PAYMENT_ID")
    private Long paymentId;

    @OneToMany(mappedBy = "order")
    @Builder.Default
    private Set<OrderItem> orderItems = new HashSet<>();

}
