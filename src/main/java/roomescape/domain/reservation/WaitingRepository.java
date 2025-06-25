package roomescape.domain.reservation;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingRepository extends JpaRepository<Waiting, Long> {
    List<Waiting> findAllByMember_Id(Long memberId);
}
