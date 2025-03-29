package gdg.swu.planmeal.config.oauth2.controller;

import gdg.swu.planmeal.config.oauth2.RegisterDto;
import gdg.swu.planmeal.member.service.RegisterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class OAuth2RegisterController {

    private final RegisterService registerService;

    @GetMapping("/oauth2/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("registerDto", new RegisterDto());
        return "registerForm";
    }

    @PostMapping("/oauth2/register")
    public String processRegister(@ModelAttribute RegisterDto registerDto) {
        return registerService.saveMember(registerDto);
    }
}
