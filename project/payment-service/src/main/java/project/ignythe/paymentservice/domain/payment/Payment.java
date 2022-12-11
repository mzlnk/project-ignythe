package project.ignythe.paymentservice.domain.payment;

import lombok.*;

import javax.persistence.*;

import java.math.BigDecimal;

import static lombok.AccessLevel.PACKAGE;

@Entity
@Getter
@Setter(PACKAGE)
@Builder
@Table(name = "PAYMENTS")
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    private static final String SEQUENCE = "PAYMENTS_SEQ";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = SEQUENCE, sequenceName = SEQUENCE, initialValue = 1)
    private Long id;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "AMOUNT")
    private BigDecimal amount;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

}
