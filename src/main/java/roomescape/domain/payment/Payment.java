package roomescape.domain.payment;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import roomescape.domain.reservation.Reservation;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private PaymentKey paymentKey;
    @Embedded
    private OrderId orderId;
    @Embedded
    private PaymentAmount amount;
    @OneToOne(cascade = CascadeType.ALL)
    private Reservation reservation;

    public Payment(
            final Long id,
            final PaymentKey paymentKey,
            final OrderId orderId,
            final PaymentAmount amount,
            final Reservation reservation
    ) {
        this.id = id;
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
        this.reservation = reservation;
    }

    public Payment(
            final PaymentKey paymentKey,
            final OrderId orderId,
            final PaymentAmount amount,
            final Reservation reservation
    ) {
        this(null, paymentKey, orderId, amount, reservation);
    }
}
