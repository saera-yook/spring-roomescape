package roomescape.presentation.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.GameTimeService;
import roomescape.presentation.response.GameTimeResponse;

@RestController
public class GameTimeController {
    private final GameTimeService timeService;

    public GameTimeController(final GameTimeService timeService) {
        this.timeService = timeService;
    }

    @GetMapping("/times")
    public List<GameTimeResponse> getAllGameTimes() {
        var times = timeService.findAllTimes();
        return GameTimeResponse.from(times);
    }
}
