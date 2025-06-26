package roomescape.domain.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import roomescape.exception.InvalidValueException;

@Embeddable
public record PaymentAmount(
        @NotNull
        @Column(name = "amount", nullable = false)
        Long value
) {
    public PaymentAmount {
        if (value < 0) {
            throw new InvalidValueException("결제 금액은 0원 이상이어야 합니다.");
        }
    }
}
