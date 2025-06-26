package roomescape.domain.payment;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import java.util.regex.Pattern;
import roomescape.exception.InvalidValueException;

@Embeddable
public record OrderId(
        @NotBlank
        @Column(name = "order_id", nullable = false, unique = true, length = 64)
        String value
) {
    private static final Pattern VALID_PATTERN = Pattern.compile("^[0-9a-zA-Z_-]{6,64}$");

    public OrderId {
        if (!VALID_PATTERN.matcher(value).matches()) {
            throw new InvalidValueException("올바르지 않은 주문번호 형식입니다.");
        }
    }
}
