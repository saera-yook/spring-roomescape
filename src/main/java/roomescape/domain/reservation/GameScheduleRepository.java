package roomescape.domain.reservation;

import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import roomescape.domain.theme.Theme;

public interface GameScheduleRepository extends JpaRepository<GameSchedule, Long> {
    Optional<GameSchedule> findByThemeAndDateAndTime(Theme theme, LocalDate date, GameTime time);
}
