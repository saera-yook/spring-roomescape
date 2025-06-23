package roomescape.presentation.response;

import roomescape.domain.theme.Theme;

public record ThemeResponse(
        long id,
        String name,
        String description,
        String thumbnail
) {
    public static ThemeResponse from(Theme theme) {
        return new ThemeResponse(
                theme.getId(),
                theme.getName().value(),
                theme.getDescription().value(),
                theme.getThumbnail().url());
    }
}
