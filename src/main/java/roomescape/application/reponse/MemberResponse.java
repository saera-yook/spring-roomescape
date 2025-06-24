package roomescape.application.reponse;

import java.util.List;
import roomescape.domain.member.Member;

public record MemberResponse(
        long id,
        String name,
        String email,
        String role
) {
    public static MemberResponse from(final Member member) {
        return new MemberResponse(member.getId(), member.getName().value(), member.getEmail().value(), member.getRole().name());
    }

    public static List<MemberResponse> from(final List<Member> members) {
        return members.stream()
                .map(MemberResponse::from)
                .toList();
    }
}
