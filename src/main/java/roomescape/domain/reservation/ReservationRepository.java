package roomescape.domain.reservation;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import roomescape.domain.theme.Theme;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsBySchedule(GameSchedule schedule);

    List<Reservation> findByMember_Id(Long memberId);

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

    @Query("""
            SELECT r.schedule.theme
            FROM Reservation r
            WHERE r.schedule.date BETWEEN :dateFrom AND :dateTo
            GROUP BY r.schedule.theme
            ORDER BY count(r) DESC
            LIMIT :count
            """)
    List<Theme> findThemesOrderByPopularity(
            @Param("dateFrom") LocalDate dateFrom,
            @Param("dateTo") LocalDate dateTo,
            @Param("count") int count
    );

    @Query("""
            SELECT r.schedule
            FROM Reservation r
            WHERE r.id = :reservationId
            """)
    Optional<GameSchedule> findScheduleById(Long id);
}
