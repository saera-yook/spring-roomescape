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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.GameTimeService;
import roomescape.application.reponse.TimeAvailabilityResponse;
import roomescape.presentation.request.CreateGameTimeRequest;
import roomescape.presentation.response.GameTimeResponse;

@RequiredArgsConstructor
@RestController
public class GameTimeController {
    private final GameTimeService timeService;

    @PostMapping("/admin/times")
    @ResponseStatus(HttpStatus.CREATED)
    public GameTimeResponse register(@Valid @RequestBody CreateGameTimeRequest request) {
        var time = timeService.register(request.startAt());
        return GameTimeResponse.from(time);
    }

    @GetMapping("/times")
    public List<GameTimeResponse> getAllGameTimes() {
        var times = timeService.findAllTimes();
        return GameTimeResponse.from(times);
    }

    @GetMapping("/times/available")
    public List<TimeAvailabilityResponse> getAllGameTimesWithAvailability(
            @RequestParam("date") LocalDate date,
            @RequestParam("themeId") long themeId
    ) {
        return timeService.getAllTimesWithAvailability(date, themeId);
    }

    @DeleteMapping("/admin/times/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        timeService.removeById(id);
    }
}
