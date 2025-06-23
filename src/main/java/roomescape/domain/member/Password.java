package roomescape.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record Password(
        @NotBlank
        @Column(name = "password", nullable = false, length = 50)
        String value
) {
    public Password {
        if (50 < value.length()) {
            throw new IllegalArgumentException("비밀번호는 50자를 넘을 수 없습니다.");
        }
    }
}
