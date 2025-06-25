package roomescape.presentation;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import roomescape.application.MemberService;
import roomescape.domain.member.LoginMember;

public class CheckAdminInterceptor implements HandlerInterceptor {
    private final MemberService memberService;

    public CheckAdminInterceptor(final MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean preHandle(final HttpServletRequest request, final HttpServletResponse response, final Object handler) {
        var loginMember = (LoginMember) request.getSession().getAttribute("LOGIN_MEMBER");
        try {
            if (!memberService.isAdminAuthorized(loginMember)) {
                response.setStatus(HttpStatus.FORBIDDEN.value());
                return false;
            }
            return true;
        } catch (IllegalArgumentException e) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return false;
        }
    }
}
