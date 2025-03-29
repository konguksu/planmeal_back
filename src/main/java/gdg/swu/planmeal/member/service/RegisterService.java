package gdg.swu.planmeal.member.service;

import gdg.swu.planmeal.config.oauth2.PrincipalDetails;
import gdg.swu.planmeal.config.oauth2.RegisterDto;
import gdg.swu.planmeal.member.entity.Member;
import gdg.swu.planmeal.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class RegisterService {

    private final MemberRepository memberRepository;
    private final HttpSession session;
    public String saveMember(RegisterDto registerDto){
        Map<String, Object> oauthUser = (Map<String, Object>) session.getAttribute("oauthUser");
        if (oauthUser == null) {
            return "redirect:/loginForm"; // 예외처리
        }

        String email = (String) oauthUser.get("email");

        Member member = Member.builder()
                .name(registerDto.getName())
                .email(email)
                .age(registerDto.getAge())
                .height(registerDto.getHeight())
                .location(registerDto.getLocation())
                .build();

        memberRepository.save(member);

        // 새 Authentication 생성 & SecurityContext 업데이트
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(new PrincipalDetails(member, oauthUser), null, null));

        session.removeAttribute("oauthUser");

        return "redirect:/home";
    }
}
