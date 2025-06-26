package roomescape.presentation.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.RoomescapeService;
import roomescape.domain.reservation.Reservation;
import roomescape.presentation.AuthenticationInfo;
import roomescape.presentation.request.CreateReservationRequest;
import roomescape.presentation.request.ReservationPaymentRequest;
import roomescape.presentation.response.MyReservationResponse;

@RequiredArgsConstructor
@RestController
public class ReservationController {
    private final RoomescapeService roomescapeService;

    @PostMapping("/reservations")
    public Reservation reserve(@AuthenticationInfo Long id, @RequestBody ReservationPaymentRequest request) {
        return roomescapeService.reserveAndPay(id, request);
    }

    @GetMapping("/reservations-mine")
    public List<MyReservationResponse> getMyReservations(@AuthenticationInfo Long id) {
        return roomescapeService.getMyReservationsAndWaitings(id);
    }
}
