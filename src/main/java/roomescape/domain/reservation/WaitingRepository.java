package roomescape.domain.reservation;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WaitingRepository extends JpaRepository<Waiting, Long> {
    @Query("""
            SELECT new roomescape.domain.reservation.MyWaiting(w.id, gs.id, gs.theme.name, gs.date, gs.time.startAt,
                    (SELECT count(w2)
                     FROM Waiting w2
                     WHERE w2.schedule.id = gs.id
                         AND w2.id <= w.id)
            )
            FROM Waiting w
            JOIN GameSchedule gs ON w.schedule.id = gs.id
            WHERE w.member.id = :memberId
            """)
    List<MyWaiting> findMyWaitingsWithOrder(Long memberId);

    Optional<Waiting> findFirstBySchedule(GameSchedule scheduleId);
}
