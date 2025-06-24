package roomescape.application;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.domain.reservation.GameSchedule;
import roomescape.domain.reservation.GameScheduleRepository;
import roomescape.domain.reservation.GameTime;
import roomescape.domain.reservation.GameTimeRepository;
import roomescape.domain.theme.Theme;
import roomescape.domain.theme.ThemeRepository;

@RequiredArgsConstructor
@Service
public class GameScheduleService {
    private final GameScheduleRepository scheduleRepository;
    private final ThemeRepository themeRepository;
    private final GameTimeRepository timeRepository;

    public GameSchedule getBy(final long themeId, final LocalDate date, final long timeId) {
        var theme = themeRepository.findById(themeId).orElseThrow();
        var time = timeRepository.findById(timeId).orElseThrow();
        return scheduleRepository.findByThemeAndDateAndTime(theme, date, time)
                .orElseGet(() -> register(theme, date, time));
    }

    private GameSchedule register(final Theme theme, final LocalDate date, final GameTime time) {
        return scheduleRepository.save(new GameSchedule(theme, date, time));
    }
}
