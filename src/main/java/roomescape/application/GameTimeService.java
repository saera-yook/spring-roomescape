package roomescape.application;

import java.util.List;
import org.springframework.stereotype.Service;
import roomescape.domain.reservation.GameTime;
import roomescape.domain.reservation.GameTimeRepository;

@Service
public class GameTimeService {
    private final GameTimeRepository timeRepository;

    public GameTimeService(final GameTimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    public List<GameTime> findAllTimes() {
        return timeRepository.findAll();
    }
}
