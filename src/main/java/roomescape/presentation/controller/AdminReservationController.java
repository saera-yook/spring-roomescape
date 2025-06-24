package roomescape.presentation.controller;

import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.ReservationService;
import roomescape.presentation.request.CreateReservationRequest;
import roomescape.presentation.response.ReservationResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/reservations")
public class AdminReservationController {
    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ReservationResponse reserve(@Valid @RequestBody CreateReservationRequest request) {
        var reservation = reservationService.register(request.memberId(), request.themeId(), request.date(),
                request.timeId());
        return ReservationResponse.from(reservation);
    }

    @GetMapping
    public List<ReservationResponse> getAllReservations() {
        var reservations = reservationService.findAllReservations();
        return ReservationResponse.from(reservations);
    }

    @GetMapping("/filter")
    public List<ReservationResponse> getReservationsByFilter(
            @RequestParam(required = false) Long memberId,
            @RequestParam(required = false) Long themeId,
            @RequestParam(required = false) LocalDate dateFrom,
            @RequestParam(required = false) LocalDate dateTo
    ) {
        var reservations = reservationService.findByFilter(memberId, themeId, dateFrom, dateTo);
        return ReservationResponse.from(reservations);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void cancel(@PathVariable("id") long id) {
        reservationService.removeById(id);
    }
}
