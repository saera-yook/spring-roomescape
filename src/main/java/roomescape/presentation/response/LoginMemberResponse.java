package roomescape.presentation.response;

import roomescape.application.reponse.MemberResponse;

public record LoginMemberResponse(String name) {
    public static LoginMemberResponse from(MemberResponse member) {
        return new LoginMemberResponse(member.name());
    }
}
