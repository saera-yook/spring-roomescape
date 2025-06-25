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
import roomescape.application.WaitingService;
import roomescape.presentation.AuthenticationInfo;
import roomescape.presentation.request.CreateWaitingRequest;
import roomescape.presentation.response.WaitingResponse;

@RequiredArgsConstructor
@RestController
public class WaitingController {
    private final WaitingService waitingService;

    @PostMapping("/waitings")
    @ResponseStatus(HttpStatus.CREATED)
    public WaitingResponse apply(@AuthenticationInfo Long id, @Valid @RequestBody CreateWaitingRequest request) {
        var waiting = waitingService.register(id, request.date(), request.timeId(), request.themeId());
        return WaitingResponse.from(waiting);
    }

    @GetMapping("/admin/waitings")
    public List<WaitingResponse> getAllWaitings() {
        var waitings = waitingService.findAllWaitings();
        return WaitingResponse.from(waitings);
    }
}
