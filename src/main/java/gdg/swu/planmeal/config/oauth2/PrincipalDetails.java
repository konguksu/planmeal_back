package gdg.swu.planmeal.config.oauth2;

import gdg.swu.planmeal.member.entity.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class PrincipalDetails implements OAuth2User {

    private final Member member;
    private final Map<String, Object> attributes;

    // OAuth 로그인용 생성자
    public PrincipalDetails(Member member, Map<String, Object> attributes) {
        this.member = member;
        this.attributes = attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null; // 필요하면 ROLE 설정 가능
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return member != null ? member.getName() : (String) attributes.get("name");
    }

    public String getEmail() {
        return member != null ? member.getName() : (String) attributes.get("email");
    }

    public boolean isNewUser() {
        return member == null;
    }
}
