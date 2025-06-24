package roomescape.domain.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsBySchedule(GameSchedule schedule);
}
