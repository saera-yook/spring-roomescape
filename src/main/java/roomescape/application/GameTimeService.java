package roomescape.application;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.application.reponse.TimeAvailabilityResponse;
import roomescape.domain.reservation.GameSchedule;
import roomescape.domain.reservation.GameScheduleRepository;
import roomescape.domain.reservation.GameTime;
import roomescape.domain.reservation.GameTimeRepository;
import roomescape.domain.reservation.ReservationRepository;
import roomescape.exception.NotFoundException;

@RequiredArgsConstructor
@Service
public class GameTimeService {
    private final GameTimeRepository timeRepository;
    private final GameScheduleRepository scheduleRepository;
    private final ReservationRepository reservationRepository;

    public GameTime register(final LocalTime startAt) {
        var gameTime = new GameTime(startAt);
        return timeRepository.save(gameTime);
    }

    public List<GameTime> findAllTimes() {
        return timeRepository.findAll();
    }

    public void removeById(final long id) {
        if (!timeRepository.existsById(id)) {
            throw new NotFoundException("해당하는 예약시간이 존재하지 않습니다. id: " + id);
        }

        timeRepository.deleteById(id);
    }

    public List<TimeAvailabilityResponse> getAllTimesWithAvailability(final LocalDate date, final long themeId) {
        var timesWithAvailability = new ArrayList<TimeAvailabilityResponse>();
        var times = findAllTimes();
        for (var time : times) {
            var schedule = scheduleRepository.findByTheme_IdAndDateAndTime(themeId, date, time);
            timesWithAvailability.add(createTimeAvailability(schedule, time));
        }
        return timesWithAvailability;
    }

    private TimeAvailabilityResponse createTimeAvailability(final Optional<GameSchedule> schedule, final GameTime time) {
        if (schedule.isEmpty()) {
            return new TimeAvailabilityResponse(time.getId(), time.getStartAt(), false);
        }

        var alreadyBooked = reservationRepository.existsBySchedule(schedule.get());
        return new TimeAvailabilityResponse(time.getId(), time.getStartAt(), alreadyBooked);
    }
}
