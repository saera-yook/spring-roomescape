package roomescape.presentation.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.ThemeService;
import roomescape.presentation.response.ThemeResponse;

@RequiredArgsConstructor
@RestController
public class AdminThemeController {
    private final ThemeService themeService;

    @GetMapping("/themes")
    public List<ThemeResponse> getAllThemes() {
        var themes = themeService.findAllThemes();
        return ThemeResponse.from(themes);
    }
}
