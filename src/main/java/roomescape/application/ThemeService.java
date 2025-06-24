package roomescape.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.domain.theme.Theme;
import roomescape.domain.theme.ThemeRepository;

@RequiredArgsConstructor
@Service
public class ThemeService {
    private final ThemeRepository themeRepository;

    public List<Theme> findAllThemes() {
        return themeRepository.findAll();
    }
}
