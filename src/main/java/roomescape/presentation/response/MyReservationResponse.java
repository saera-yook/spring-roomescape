package roomescape.presentation.response;

import java.time.LocalDate;
import java.util.List;
import roomescape.domain.reservation.Reservation;
import roomescape.domain.reservation.Waiting;

public record MyReservationResponse(
        long reservationId,
        ThemeResponse theme,
        LocalDate date,
        GameTimeResponse time,
        String status,
        String paymentKey,
        String amount
) {
    public static MyReservationResponse from(Reservation reservation) {
        var schedule = reservation.getSchedule();

        return new MyReservationResponse(
                reservation.getId(),
                ThemeResponse.from(schedule.getTheme()),
                schedule.getDate(),
                GameTimeResponse.from(schedule.getTime()),
                "예약",
                "",
                ""
        );
    }

    public static MyReservationResponse from(Waiting waiting) {
        var schedule = waiting.getSchedule();

        return new MyReservationResponse(
                waiting.getId(),
                ThemeResponse.from(schedule.getTheme()),
                schedule.getDate(),
                GameTimeResponse.from(schedule.getTime()),
                "예약대기",
                "",
                ""
        );
    }

    public static List<MyReservationResponse> fromReservations(List<Reservation> reservations) {
        return reservations.stream()
                .map(MyReservationResponse::from)
                .toList();
    }

    public static List<MyReservationResponse> fromWaitings(List<Waiting> waitings) {
        return waitings.stream()
                .map(MyReservationResponse::from)
                .toList();
    }
}
