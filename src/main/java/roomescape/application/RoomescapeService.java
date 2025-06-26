package roomescape.application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.domain.reservation.Reservation;
import roomescape.presentation.request.ReservationPaymentRequest;
import roomescape.presentation.response.MyReservationResponse;

@RequiredArgsConstructor
@Service
public class RoomescapeService {
    private final ReservationService reservationService;
    private final WaitingService waitingService;
    private final PaymentService paymentService;

    public Reservation reserveAndPay(final Long id, final ReservationPaymentRequest request) {
        var reservation = reservationService.register(id, request.themeId(), request.date(), request.timeId());
        paymentService.pay(new PaymentConfirmRequest(request.paymentKey(), request.orderId(), request.amount()), reservation);
        return reservation;
    }

    public List<MyReservationResponse> getMyReservationsAndWaitings(final long memberId) {
        var reservations = reservationService.findAllByMemberId(memberId);
        var waitings = waitingService.findMyWaitingsWithOrder(memberId);

        var responses = new ArrayList<MyReservationResponse>();
        responses.addAll(MyReservationResponse.fromReservations(reservations));
        responses.addAll(MyReservationResponse.fromWaitings(waitings));

        responses.sort(Comparator
                .comparing(MyReservationResponse::date)
                .thenComparing(MyReservationResponse::startAt)
        );
        return responses;
    }

    public void cancelReservation(final long reservationId) {
        var schedule = reservationService.getScheduleByReservationId(reservationId);
        reservationService.removeById(reservationId);
        waitingService.approveFirstWaitingOn(schedule);
    }
}
