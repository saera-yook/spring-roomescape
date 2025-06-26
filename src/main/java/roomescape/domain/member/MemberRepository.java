package roomescape.domain.member;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsById(Long id);

    Optional<Member> findByEmailAndPassword(Email email, Password password);
}
