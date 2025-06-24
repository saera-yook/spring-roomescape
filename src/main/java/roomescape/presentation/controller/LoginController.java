package roomescape.presentation.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.MemberService;
import roomescape.domain.member.LoginMember;
import roomescape.presentation.request.LoginRequest;
import roomescape.presentation.response.LoginMemberResponse;

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

    @GetMapping("/login/check")
    public LoginMemberResponse check(HttpSession session) {
        var loginMember = (LoginMember) session.getAttribute(SESSION_KEY);
        var member = memberService.findBy(loginMember);
        return LoginMemberResponse.from(member);
    }
}
