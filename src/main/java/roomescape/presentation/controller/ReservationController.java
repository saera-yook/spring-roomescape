package roomescape.presentation.controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.ReservationService;
import roomescape.domain.member.LoginMember;
import roomescape.presentation.response.MyReservationResponse;

@RequiredArgsConstructor
@RestController
public class ReservationController {
    private final ReservationService reservationService;

    @GetMapping("/reservations-mine")
    public List<MyReservationResponse> getMyReservations(HttpSession session) {
        var loginMember = (LoginMember) session.getAttribute("LOGIN_MEMBER");
        var reservations = reservationService.findAllByMemberId(loginMember);
        return MyReservationResponse.from(reservations);
    }
}
