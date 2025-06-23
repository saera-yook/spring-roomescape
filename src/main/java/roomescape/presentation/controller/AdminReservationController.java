package roomescape.presentation.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.ReservationService;
import roomescape.presentation.response.ReservationResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/reservations")
public class AdminReservationController {
    private final ReservationService reservationService;

    @GetMapping
    public List<ReservationResponse> getAllReservations() {
        var reservations = reservationService.findAllReservations();
        return ReservationResponse.from(reservations);
    }
}
