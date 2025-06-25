package roomescape.domain.theme;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import roomescape.exception.InvalidInputException;

@Embeddable
public record Thumbnail(
        @NotBlank
        @Column(name = "thumbnail", nullable = false)
        String url
) {
    public Thumbnail {
        if (255 < url.length()) {
            throw new InvalidInputException("썸네일 url은 255자를 넘을 수 없습니다.");
        }
    }
}
