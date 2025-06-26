package roomescape.presentation.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import roomescape.domain.reservation.MyWaitingWithOrder;
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

    public static MyReservationResponse from(MyWaitingWithOrder waiting) {
        return new MyReservationResponse(
                waiting.waiting().waitingId(),
                waiting.waiting().themeName().value(),
                waiting.waiting().date(),
                waiting.waiting().startAt(),
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

    public static List<MyReservationResponse> fromWaitings(List<MyWaitingWithOrder> waitings) {
        return waitings.stream()
                .map(MyReservationResponse::from)
                .toList();
    }
}
