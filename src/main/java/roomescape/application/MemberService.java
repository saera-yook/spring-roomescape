package roomescape.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.application.reponse.MemberResponse;
import roomescape.domain.member.MemberRepository;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public List<MemberResponse> findAllMembers() {
        var members = memberRepository.findAll();
        return MemberResponse.from(members);
    }
}
