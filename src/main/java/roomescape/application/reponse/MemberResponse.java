package roomescape.application.reponse;

import java.util.List;
import roomescape.domain.member.Member;

public record MemberResponse(
        long id,
        String name,
        String email
) {
    public static MemberResponse from(final Member member) {
        return new MemberResponse(member.getId(), member.getName().value(), member.getEmail().value());
    }

    public static List<MemberResponse> from(final List<Member> members) {
        return members.stream()
                .map(MemberResponse::from)
                .toList();
    }
}
