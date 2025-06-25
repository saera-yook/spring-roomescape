package roomescape.presentation.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.ReservationService;
import roomescape.presentation.AuthenticationInfo;
import roomescape.presentation.response.MyReservationResponse;

@RequiredArgsConstructor
@RestController
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/reservations-mine")
    public List<MyReservationResponse> getMyReservations(@AuthenticationInfo Long id) {
        var reservations = reservationService.findAllByMemberId(id);
        return MyReservationResponse.from(reservations);
    }
}
