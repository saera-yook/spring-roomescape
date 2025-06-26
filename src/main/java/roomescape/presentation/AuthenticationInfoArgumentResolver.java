package roomescape.presentation;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import roomescape.application.MemberService;
import roomescape.domain.member.LoginMember;
import roomescape.exception.AuthenticationException;

public class AuthenticationInfoArgumentResolver implements HandlerMethodArgumentResolver {
    private final MemberService memberService;

    public AuthenticationInfoArgumentResolver(final MemberService memberService) {
        this.memberService = memberService;
    }

    @Override
    public boolean supportsParameter(final MethodParameter parameter) {
        return parameter.hasParameterAnnotation(AuthenticationInfo.class);
    }

    @Override
    public Long resolveArgument(final MethodParameter parameter, final ModelAndViewContainer mavContainer,
                                  final NativeWebRequest webRequest, final WebDataBinderFactory binderFactory) {
        var request = webRequest.getNativeRequest(HttpServletRequest.class);
        var loginMember = (LoginMember) request.getSession().getAttribute("LOGIN_MEMBER");

        if (memberService.existsBy(loginMember)) {
            return loginMember.id();
        }
        throw new AuthenticationException("인증되지 않은 회원입니다. id: " + loginMember.id());
    }
}
