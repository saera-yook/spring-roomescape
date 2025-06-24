package roomescape.application;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.domain.reservation.ReservationRepository;
import roomescape.domain.theme.Description;
import roomescape.domain.theme.Theme;
import roomescape.domain.theme.ThemeName;
import roomescape.domain.theme.ThemeRepository;
import roomescape.domain.theme.Thumbnail;
import roomescape.exception.NotFoundException;

@RequiredArgsConstructor
@Service
public class ThemeService {
    private final ThemeRepository themeRepository;
    private final ReservationRepository reservationRepository;

    public Theme register(final String name, final String description, final String thumbnail) {
        var theme = new Theme(new ThemeName(name), new Description(description), new Thumbnail(thumbnail));
        return themeRepository.save(theme);
    }

    public List<Theme> findAllThemes() {
        return themeRepository.findAll();
    }

    public List<Theme> findThemesOrderByPopularity(final LocalDate dateFrom, final LocalDate dateTo, final int count) {
        return reservationRepository.findThemesOrderByPopularity(dateFrom, dateTo, count);
    }

    public void removeById(final long id) {
        if (!themeRepository.existsById(id)) {
            throw new NotFoundException("해당하는 테마가 존재하지 않습니다. id: " + id);
        }

        themeRepository.deleteById(id);
    }
}
