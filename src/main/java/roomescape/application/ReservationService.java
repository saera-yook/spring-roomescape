package roomescape.application;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.domain.member.MemberRepository;
import roomescape.domain.reservation.Reservation;
import roomescape.domain.reservation.ReservationRepository;
import roomescape.exception.NotFoundException;

@RequiredArgsConstructor
@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final GameScheduleService scheduleService;

    public Reservation register(final long memberId, final long themeId, final LocalDate date, final long timeId) {
        var schedule = scheduleService.getBy(themeId, date, timeId);
        if (reservationRepository.existsBySchedule(schedule)) {
            throw new IllegalArgumentException("이미 예약된 게임 일정입니다. id: " + schedule.getId());
        }

        var member = memberRepository.findById(memberId).orElseThrow();
        var reservation = new Reservation(member, schedule);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAllReservations() {
        return reservationRepository.findAll();
    }

    public void removeById(final long id) {
        if (!reservationRepository.existsById(id)) {
            throw new NotFoundException("해당하는 예약이 존재하지 않습니다. id: " + id);
        }

        reservationRepository.deleteById(id);
    }
}
