package roomescape.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;

@Embeddable
public record MemberName(
        @NotBlank
        @Column(name = "name", nullable = false, length = 20)
        String value
) {
    public MemberName {
        if (20 < value.length()) {
            throw new IllegalArgumentException("이름은 20자를 넘을 수 없습니다.");
        }
    }
}
