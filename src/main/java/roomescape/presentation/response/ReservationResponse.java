package roomescape.presentation.response;

import java.time.LocalDate;
import java.util.List;
import roomescape.application.reponse.MemberResponse;
import roomescape.domain.reservation.Reservation;

public record ReservationResponse(
        long id,
        MemberResponse member,
        ThemeResponse theme,
        LocalDate date,
        GameTimeResponse time
) {
    public static ReservationResponse from(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                MemberResponse.from(reservation.getMember()),
                ThemeResponse.from(reservation.getSchedule().getTheme()),
                reservation.getSchedule().getDate(),
                GameTimeResponse.from(reservation.getSchedule().getTime())
        );
    }

    public static List<ReservationResponse> from(List<Reservation> reservations) {
        return reservations.stream()
                .map(ReservationResponse::from)
                .toList();
    }
}
