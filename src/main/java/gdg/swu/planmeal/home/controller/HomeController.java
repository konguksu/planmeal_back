package gdg.swu.planmeal.home.controller;

import gdg.swu.planmeal.config.oauth2.PrincipalDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        model.addAttribute("member", principalDetails.getMember());
        return "home";
    }
}
