package roomescape.application;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.domain.member.MemberRepository;
import roomescape.domain.reservation.ReservationRepository;
import roomescape.domain.reservation.Waiting;
import roomescape.domain.reservation.WaitingRepository;
import roomescape.exception.BusinessRuleViolationException;

@RequiredArgsConstructor
@Service
public class WaitingService {
    private final WaitingRepository waitingRepository;
    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final GameScheduleService scheduleService;

    public Waiting register(final Long memberId, final LocalDate date, final long timeId, final long themeId) {
        var schedule = scheduleService.getBy(themeId, date, timeId);
        if (!reservationRepository.existsBySchedule(schedule)) {
            throw new BusinessRuleViolationException("예약 가능한 게임입니다. 예약하기를 이용해주세요.");
        }

        var member = memberRepository.findById(memberId).orElseThrow();
        return waitingRepository.save(new Waiting(member, schedule));
    }

    public List<Waiting> findAllWaitings() {
        return waitingRepository.findAll();
    }
}
