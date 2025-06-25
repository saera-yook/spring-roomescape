package roomescape.presentation.response;

import java.time.LocalDate;
import java.util.List;
import roomescape.domain.reservation.Reservation;

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
        return new MyReservationResponse(
                reservation.getId(),
                ThemeResponse.from(reservation.getSchedule().getTheme()),
                reservation.getSchedule().getDate(),
                GameTimeResponse.from(reservation.getSchedule().getTime()),
                "예약",
                "",
                ""
        );
    }

    public static List<MyReservationResponse> from(List<Reservation> reservations) {
        return reservations.stream()
                .map(MyReservationResponse::from)
                .toList();
    }
}
