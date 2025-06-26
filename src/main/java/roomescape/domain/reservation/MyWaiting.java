package roomescape.domain.reservation;

import java.time.LocalDate;
import java.time.LocalTime;
import roomescape.domain.theme.ThemeName;

public record MyWaiting(
        long waitingId,
        long scheduleId,
        ThemeName themeName,
        LocalDate date,
        LocalTime startAt,
        long order
) {
}
