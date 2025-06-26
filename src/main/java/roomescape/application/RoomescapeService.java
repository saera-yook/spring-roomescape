package roomescape.application;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.presentation.response.MyReservationResponse;

@RequiredArgsConstructor
@Service
public class RoomescapeService {
    private final ReservationService reservationService;
    private final WaitingService waitingService;

    public List<MyReservationResponse> getMyReservationsAndWaitings(final long memberId) {
        var reservations = reservationService.findAllByMemberId(memberId);
        var waitings = waitingService.findMyWaitings(memberId);

        var responses = new ArrayList<MyReservationResponse>();
        responses.addAll(MyReservationResponse.fromReservations(reservations));
        responses.addAll(MyReservationResponse.fromWaitings(waitings));

        responses.sort(Comparator
                .comparing(MyReservationResponse::date)
                .thenComparing(MyReservationResponse::startAt)
        );
        return responses;
    }
}
