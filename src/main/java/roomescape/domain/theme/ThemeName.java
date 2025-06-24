package roomescape.domain.theme;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import roomescape.exception.InvalidInputException;

@Embeddable
public record ThemeName(
        @NotBlank
        @Column(name = "name", nullable = false, length = 20)
        String value
) {
    public ThemeName {
        if (20 < value.length()) {
            throw new InvalidInputException("테마 이름은 20자를 넘을 수 없습니다.");
        }
    }
}
