package gdg.swu.planmeal.oauth2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/loginForm")
    public String login() {
        return "loginForm";
    }
}
