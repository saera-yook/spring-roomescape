package roomescape.presentation.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roomescape.application.MemberService;
import roomescape.application.reponse.MemberResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/admin/members")
public class AdminMemberController {
    private final MemberService memberService;

    @GetMapping
    public List<MemberResponse> getAllMembers() {
        return memberService.findAllMembers();
    }
}
