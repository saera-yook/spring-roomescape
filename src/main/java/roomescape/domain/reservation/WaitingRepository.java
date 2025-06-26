package roomescape.domain.reservation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WaitingRepository extends JpaRepository<Waiting, Long> {
    List<Waiting> findAllByMember_Id(Long memberId);

    @Query("""
            SELECT new roomescape.domain.reservation.MyWaiting(w.id, gs.id, gs.theme.name, gs.date, gs.time.startAt)
            FROM Waiting w
            LEFT JOIN GameSchedule gs ON w.schedule.id = gs.id
            WHERE w.member.id = :memberId
            """)
    List<MyWaiting> findMyWaitings(Long memberId);

    @Query("""
            SELECT count(w)
            FROM Waiting w
            WHERE w.schedule.id = :scheduleId AND w.id <= :waitingId
            """)
    long countWaitings(long waitingId, long scheduleId);
}
