package roomescape.presentation.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.RoomescapeService;
import roomescape.presentation.AuthenticationInfo;
import roomescape.presentation.response.MyReservationResponse;

@RequiredArgsConstructor
@RestController
public class ReservationController {
    private final RoomescapeService roomescapeService;

    @GetMapping("/reservations-mine")
    public List<MyReservationResponse> getMyReservations(@AuthenticationInfo Long id) {
        return roomescapeService.getMyReservationsAndWaitings(id);
    }
}
