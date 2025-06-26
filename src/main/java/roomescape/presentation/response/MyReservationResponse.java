package roomescape.presentation.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import roomescape.domain.reservation.MyWaiting;
import roomescape.domain.reservation.Reservation;

public record MyReservationResponse(
        long id,
        String themeName,
        LocalDate date,
        LocalTime startAt,
        String status,
        String paymentKey,
        String amount
) {
    public static MyReservationResponse from(Reservation reservation) {
        var schedule = reservation.getSchedule();

        return new MyReservationResponse(
                reservation.getId(),
                schedule.getTheme().getName().value(),
                schedule.getDate(),
                schedule.getTime().getStartAt(),
                "예약",
                "",
                ""
        );
    }

    public static MyReservationResponse from(MyWaiting waiting) {
        return new MyReservationResponse(
                waiting.waitingId(),
                waiting.themeName().value(),
                waiting.date(),
                waiting.startAt(),
                waiting.order() + "번째 예약대기",
                "",
                ""
        );
    }

    public static List<MyReservationResponse> fromReservations(List<Reservation> reservations) {
        return reservations.stream()
                .map(MyReservationResponse::from)
                .toList();
    }

    public static List<MyReservationResponse> fromWaitings(List<MyWaiting> waitings) {
        return waitings.stream()
                .map(MyReservationResponse::from)
                .toList();
    }
}
