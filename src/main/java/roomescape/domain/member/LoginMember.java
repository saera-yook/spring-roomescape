package roomescape.domain.member;

import roomescape.application.reponse.MemberResponse;

public record LoginMember(long id) {
    public static LoginMember from(MemberResponse member) {
        return new LoginMember(member.id());
    }
}
