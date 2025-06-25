package roomescape.domain.theme;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import roomescape.exception.InvalidInputException;

@Embeddable
public record Description(
        @NotBlank
        @Column(name = "description", nullable = false)
        String value
) {
    public Description {
        if (255 < value.length()) {
            throw new InvalidInputException("테마 설명은 255자를 넘을 수 없습니다.");
        }
    }
}
