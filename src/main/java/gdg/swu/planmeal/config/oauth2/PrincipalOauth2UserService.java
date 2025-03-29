package gdg.swu.planmeal.config.oauth2;

import gdg.swu.planmeal.member.entity.Member;
import gdg.swu.planmeal.member.repository.MemberRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final HttpSession httpSession;

    public PrincipalOauth2UserService(MemberRepository memberRepository, HttpSession httpSession) {
        this.memberRepository = memberRepository;
        this.httpSession = httpSession;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        Map<String, Object> attributes = super.loadUser(userRequest).getAttributes();

        Member member = memberRepository.findByEmail((String) attributes.get("email")).orElse(null);

        if (member == null) {
            httpSession.setAttribute("oauthUser", attributes);
        }

        return new PrincipalDetails(member, attributes);
    }
}
