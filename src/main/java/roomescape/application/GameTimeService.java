package roomescape.application;

import java.time.LocalTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.domain.reservation.GameTime;
import roomescape.domain.reservation.GameTimeRepository;

@RequiredArgsConstructor
@Service
public class GameTimeService {
    private final GameTimeRepository timeRepository;

    public GameTime register(final LocalTime startAt) {
        var gameTime = new GameTime(startAt);
        return timeRepository.save(gameTime);
    }

    public List<GameTime> findAllTimes() {
        return timeRepository.findAll();
    }

    public void removeById(final long id) {
        if (!timeRepository.existsById(id)) {
            throw new IllegalArgumentException("해당하는 예약시간이 존재하지 않습니다. id: " + id);
        }

        timeRepository.deleteById(id);
    }
}
