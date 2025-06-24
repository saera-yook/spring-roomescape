package roomescape.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import roomescape.application.reponse.MemberResponse;
import roomescape.domain.member.Email;
import roomescape.domain.member.LoginMember;
import roomescape.domain.member.MemberRepository;
import roomescape.domain.member.Password;
import roomescape.exception.InvalidInputException;
import roomescape.exception.NotFoundException;

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResponse findBy(final LoginMember loginMember) {
        var member = memberRepository.findById(loginMember.id())
                .orElseThrow(() -> new NotFoundException("해당하는 회원이 존재하지 않습니다. id: " + loginMember.id()));
        return MemberResponse.from(member);
    }

    public MemberResponse findByMemberInfo(final String email, final String password) {
        var member = memberRepository.findByEmailAndPassword(new Email(email), new Password(password))
                .orElseThrow(() -> new InvalidInputException("이메일 또는 비밀번호가 올바르지 않습니다."));
        return MemberResponse.from(member);
    }

    public List<MemberResponse> findAllMembers() {
        var members = memberRepository.findAll();
        return MemberResponse.from(members);
    }
}
