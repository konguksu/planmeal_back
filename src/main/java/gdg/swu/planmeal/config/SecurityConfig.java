package gdg.swu.planmeal.config;

import gdg.swu.planmeal.config.oauth2.CustomOAuth2SuccessHandler;
import gdg.swu.planmeal.config.oauth2.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {

    private final PrincipalOauth2UserService principalOauth2UserService;
    private final CustomOAuth2SuccessHandler customOAuth2SuccessHandler;
    private final CorsConfig corsConfig;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.addFilter(corsConfig.corsFilter());

        http.authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/loginForm", "oauth2/**", "/css/**", "/js/**", "/images/**").permitAll()
                .anyRequest().authenticated()); // 나머지 경로는 로그인 필요

        http.oauth2Login(oauth ->
                oauth
                        .loginPage("/loginForm")
                        .userInfoEndpoint(userInfo ->
                                userInfo.userService(principalOauth2UserService))
                        .successHandler(customOAuth2SuccessHandler)
        );
        return http.build();
    }
}
