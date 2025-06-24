package roomescape.presentation.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalTime;
import java.util.List;
import roomescape.domain.reservation.GameTime;

public record GameTimeResponse(
        long id,
        @JsonFormat(pattern = "HH:mm")
        LocalTime startAt
) {
    public static GameTimeResponse from(GameTime gameTime) {
        return new GameTimeResponse(gameTime.getId(), gameTime.getStartAt());
    }

    public static List<GameTimeResponse> from(List<GameTime> gameTimes) {
        return gameTimes.stream()
                .map(GameTimeResponse::from)
                .toList();
    }
}
