package roomescape.domain.reservation;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsBySchedule(GameSchedule schedule);

    @Query("""
            SELECT r
            FROM Reservation r
            WHERE (:memberId IS NULL OR r.member.id = :memberId)
                AND (:themeId IS NULL OR r.schedule.theme.id = :themeId)
                AND (:dateFrom IS NULL OR r.schedule.date >= :dateFrom)
                AND(:dateTo IS NULL OR r.schedule.date <= :dateTo)
            """)
    List<Reservation> findByMemberIdAndThemeIdAndDateRange(
            @Param("memberId") Long memberId,
            @Param("themeId") Long themeId,
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo
    );
}
