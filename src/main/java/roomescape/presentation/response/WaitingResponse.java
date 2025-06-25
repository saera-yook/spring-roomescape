package roomescape.presentation.response;

import java.time.LocalDate;
import java.util.List;
import roomescape.application.reponse.MemberResponse;
import roomescape.domain.reservation.Waiting;

public record WaitingResponse(
        long id,
        MemberResponse member,
        ThemeResponse theme,
        LocalDate date,
        GameTimeResponse time
) {
    public static WaitingResponse from(Waiting waiting) {
        var schedule = waiting.getSchedule();

        return new WaitingResponse(
                waiting.getId(),
                MemberResponse.from(waiting.getMember()),
                ThemeResponse.from(schedule.getTheme()),
                schedule.getDate(),
                GameTimeResponse.from(schedule.getTime())
        );
    }

    public static List<WaitingResponse> from(List<Waiting> waitings) {
        return waitings.stream()
                .map(WaitingResponse::from)
                .toList();
    }
}
