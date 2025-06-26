package roomescape.domain.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import roomescape.exception.InvalidValueException;

@Embeddable
public record PaymentKey(
        @NotBlank
        @Column(name = "payment_key", nullable = false, unique = true, length = 200)
        String value
) {
    public PaymentKey {
        if (200 < value.length()) {
            throw new InvalidValueException("결제 키 값은 200자를 넘을 수 없습니다.");
        }
    }
}
