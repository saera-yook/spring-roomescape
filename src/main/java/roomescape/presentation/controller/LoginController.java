package roomescape.presentation.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.MemberService;
import roomescape.domain.member.LoginMember;
import roomescape.presentation.request.LoginRequest;

@RequiredArgsConstructor
@RestController
public class LoginController {
    private static final String SESSION_KEY = "LOGIN_MEMBER";

    private final MemberService memberService;

    @PostMapping("/login")
    public void login(@Valid @RequestBody LoginRequest request, HttpSession session) {
        var member = memberService.findByMemberInfo(request.email(), request.password());

        session.setAttribute(SESSION_KEY, LoginMember.from(member));
    }
}
