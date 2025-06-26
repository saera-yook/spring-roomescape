package roomescape.application;

import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.domain.member.MemberRepository;
import roomescape.domain.reservation.MyWaitingWithOrder;
import roomescape.domain.reservation.ReservationRepository;
import roomescape.domain.reservation.Waiting;
import roomescape.domain.reservation.WaitingRepository;
import roomescape.exception.BusinessRuleViolationException;
import roomescape.exception.NotFoundException;

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

    public List<Waiting> findAllByMemberId(final long memberId) {
        return waitingRepository.findAllByMember_Id(memberId);
    }

    public List<MyWaitingWithOrder> findMyWaitings(final long memberId) {
        var myWaitings = waitingRepository.findMyWaitings(memberId);
        return myWaitings.stream()
                .map(waiting -> new MyWaitingWithOrder(waiting, countOrder(waiting.waitingId(), waiting.scheduleId())))
                .toList();
    }

    public long countOrder(final long waitingId, final long scheduleId) {
        return waitingRepository.countWaitings(waitingId, scheduleId);
    }

    public void removeById(final long id) {
        if (!waitingRepository.existsById(id)) {
            throw new NotFoundException("해당하는 예약 대기가 존재하지 않습니다. id: " + id);
        }

        waitingRepository.deleteById(id);
    }
}
