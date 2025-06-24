package roomescape.domain.reservation;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GameTimeRepository extends JpaRepository<GameTime, Long> {
}
