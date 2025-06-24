package roomescape.domain.member;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import java.util.regex.Pattern;
import roomescape.exception.InvalidInputException;

@Embeddable
public record Email(
        @NotBlank
        @Column(name = "email", nullable = false, unique = true, length = 50)
        String value
) {
    private static final Pattern VALID_PATTERN = Pattern.compile("\\w+@\\w+\\.\\w+");

    public Email {
        if (!VALID_PATTERN.matcher(value).matches()) {
            throw new InvalidInputException("올바른 형식의 이메일을 입력해주세요.");
        }

        if (50 < value.length()) {
            throw new InvalidInputException("이메일은 50자를 넘을 수 없습니다.");
        }
    }
}
