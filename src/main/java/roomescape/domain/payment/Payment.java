package roomescape.domain.payment;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public Payment(final Long id, final PaymentKey paymentKey, final OrderId orderId, final PaymentAmount amount) {
        this.paymentKey = paymentKey;
        this.orderId = orderId;
        this.amount = amount;
    }

    public Payment(final PaymentKey paymentKey, final OrderId orderId, final PaymentAmount amount) {
        this(null, paymentKey, orderId, amount);
    }
}
