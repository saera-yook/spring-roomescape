package roomescape.presentation.controller;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.GameTimeService;
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
}
