package roomescape.presentation.controller;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.ThemeService;
import roomescape.presentation.request.CreateThemeRequest;
import roomescape.presentation.response.ThemeResponse;

@RequiredArgsConstructor
@RestController
public class AdminThemeController {
    private final ThemeService themeService;

    @PostMapping("/admin/themes")
    @ResponseStatus(HttpStatus.CREATED)
    public ThemeResponse register(@Valid @RequestBody CreateThemeRequest request) {
        var theme = themeService.register(request.name(), request.description(), request.thumbnail());
        return ThemeResponse.from(theme);
    }

    @GetMapping("/themes")
    public List<ThemeResponse> getAllThemes() {
        var themes = themeService.findAllThemes();
        return ThemeResponse.from(themes);
    }

    @GetMapping("/themes/popular")
    public List<ThemeResponse> getPopularThemes(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam int count
    ) {
        var themes = themeService.findThemesOrderByPopularity(startDate, endDate, count);
        return ThemeResponse.from(themes);
    }

    @DeleteMapping("/admin/themes/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        themeService.removeById(id);
    }
}
